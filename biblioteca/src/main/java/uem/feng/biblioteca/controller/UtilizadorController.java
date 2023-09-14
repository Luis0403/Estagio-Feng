package uem.feng.biblioteca.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uem.feng.biblioteca.model.Utilizador;
import uem.feng.biblioteca.repository.UtilizadorRepository;
import uem.feng.biblioteca.service.UtilizadorService;

@RestController
@RequestMapping("/utilizadores")
public class UtilizadorController {
	
	@Autowired
	UtilizadorRepository utilizadorRepository;
	
	@Autowired
	 private UtilizadorService utilizadorService;
	
   

	
	//@CrossOrigin(maxAge=10, origins = {"http://localhost:61468"})
	@GetMapping
	public List<Utilizador> listar(){
		return utilizadorRepository.findAll();	
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscar(@PathVariable Long codigo ){
		Utilizador utilizador = utilizadorRepository.findByCodigo(codigo);
		return utilizador != null? ResponseEntity.ok(utilizador): ResponseEntity.notFound().build();	
	}
	
	@PostMapping
	public ResponseEntity<Utilizador> salvar(@Valid @RequestBody Utilizador utilizador, HttpServletResponse response) {		
		
		utilizadorService.verificarDuplicacaoDeEmailENome(utilizador);
		
		utilizadorService.saveUtilizador(utilizador);
		return ResponseEntity.status(HttpStatus.CREATED).body(utilizador);		
	}
	
	@PutMapping ("/{codigo}")
	public ResponseEntity<?> atualizar(@PathVariable Long codigo, @RequestBody Utilizador utilizador){
		
		Utilizador utilizadorSalvo = utilizadorRepository.findByCodigo(codigo);
		BeanUtils.copyProperties(utilizador, utilizadorSalvo,"codigo");
		utilizadorService.saveUtilizador(utilizadorSalvo);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(utilizador);
	}
	
	
	//Nao precisamos de apagar o utilizador atraves do seu controller mas sim dos outros controllers em que
	// a sua chave e multipla para as respectivas tabelas
//	@DeleteMapping("/{codigo}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void remover(@PathVariable ("codigo") Utilizador utilizador) {
//		
//		utilizadorRepository.delete(utilizador);
//	}
	

	

}
