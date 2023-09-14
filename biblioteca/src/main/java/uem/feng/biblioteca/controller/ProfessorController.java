package uem.feng.biblioteca.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import uem.feng.biblioteca.event.RecursoCriadoEvent;
import uem.feng.biblioteca.model.Professor;
import uem.feng.biblioteca.model.Utilizador;
import uem.feng.biblioteca.model.dto.ProfessorUtilizadorDTO;
import uem.feng.biblioteca.repository.ProfessorRepository;
import uem.feng.biblioteca.repository.RoleRepository;
import uem.feng.biblioteca.service.ProfessorService;
import uem.feng.biblioteca.service.UtilizadorService;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	
	@Autowired
	private UtilizadorService utilizadorService;
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	//criar professores
	@PostMapping
	public ResponseEntity <Professor> criar (@RequestBody ProfessorUtilizadorDTO professorUtilizadorDTO, HttpServletResponse response) {
		
		Professor professor = professorUtilizadorDTO.getProfessor();
		professorService.verificarConsistencia(professor);
		
		Utilizador utilizador = professorUtilizadorDTO.getUtilizador();
		utilizadorService.verificarDuplicacaoDeEmailENome(utilizador);
		
		//adicionar roles ao utilizador
		utilizador.getRoles().add(roleRepository.findByNome("ROLE_"+utilizador.getTipo()));
		Utilizador utilizadorSalvo = utilizadorService.saveUtilizador(utilizador);
		
		professor.setUtilizador(utilizadorSalvo);
		Professor professorSalvo=professorRepository.save(professor);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, professorSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(professorSalvo);
	}
	
	
	//listar professores
	@GetMapping
	public List<Professor> listar(){
		return professorRepository.findAll();
		
	}
	
	//buscar professor pelo codigo
	@GetMapping("/{codigo}")
	public Professor buscarPeloCodigo(@PathVariable ("codigo") long codigo) {
	
		return professorRepository.findByCodigo(codigo);
	}
	
	//remover um professor pelo codigo
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable ("codigo") Long codigo, Professor professor) {
		Professor pfr=professorService.buscarPeloCodigo(codigo);
		professorRepository.delete(pfr);
		//utilizadorRepository.delete(pfr.getUtilizador());
	}
	
	//actualizando professor pelo codigo
	@PutMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Professor> actualizar(@PathVariable ("codigo") long codigo, Professor professor){
		
		Professor professorSalvo=professorService.actualizar(codigo,professor);
		return ResponseEntity.ok(professorSalvo);
	}

}
