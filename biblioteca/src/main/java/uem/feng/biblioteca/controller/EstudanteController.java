package uem.feng.biblioteca.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
import uem.feng.biblioteca.model.Estudante;
import uem.feng.biblioteca.model.Utilizador;
import uem.feng.biblioteca.model.dto.EstudanteUtilizadorDTO;
import uem.feng.biblioteca.repository.EstudanteRepository;
import uem.feng.biblioteca.repository.RoleRepository;
import uem.feng.biblioteca.repository.UtilizadorRepository;
import uem.feng.biblioteca.service.EstudanteServices;
import uem.feng.biblioteca.service.UtilizadorService;

@RestController
@RequestMapping("/estudante")
public class EstudanteController {
	
	@Autowired
	private EstudanteRepository estudanteRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private EstudanteServices estudanteServices;
	
	@Autowired
	private UtilizadorService utilizadorService;
	
	@Autowired
	private UtilizadorRepository utilizadorRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping
	public ResponseEntity<List<Estudante>> listarEstudante(){
		
		return ResponseEntity.ok(estudanteRepository.findAll());
	}
	

	@PostMapping("/criarEstudante")
	public ResponseEntity<Estudante> criarEstudante(@RequestBody @Valid EstudanteUtilizadorDTO estudanteDto, HttpServletResponse response){
		
		Estudante estudante=estudanteDto.getEstudante();
		Utilizador utilizador=estudanteDto.getUtilizador();
		
		
		estudanteServices.verificarConsistencia(estudante);
		
		utilizadorService.verificarDuplicacaoDeEmailENome(utilizador);
		
		//adicionar o utlizador as roles
		utilizador.getRoles().add(roleRepository.findByNome("ROLE_"+utilizador.getTipo()));
		
		Utilizador utilizadorSalvo=utilizadorService.saveUtilizador(utilizador);
		
		
		estudanteDto.getEstudante().setUtilizador(utilizadorSalvo);
		Estudante estudante2=estudanteRepository.save(estudante);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, estudante2.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(estudante2);
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Estudante> buscarEstudanteByCode(@PathVariable @Valid Long codigo){
		
		return ResponseEntity.ok(estudanteServices.buscarPeloCodigo(codigo));
	}
	
	
	@PutMapping("/{codigo}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void actualizar_Estudante(@PathVariable Long codigo, @RequestBody  Estudante estudante){
		estudanteServices.actualizarEstudante(codigo,estudante);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{codigo}")
	public void apagar_Estudante(@PathVariable @Valid  Long codigo) {
		Estudante estudante=estudanteServices.buscarPeloCodigo(codigo);
		estudanteRepository.delete(estudante);
		utilizadorRepository.delete(estudante.getUtilizador());
	}
}
