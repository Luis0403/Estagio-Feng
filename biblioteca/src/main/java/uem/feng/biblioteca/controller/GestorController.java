package uem.feng.biblioteca.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uem.feng.biblioteca.event.RecursoCriadoEvent;
import uem.feng.biblioteca.model.Gestor;
import uem.feng.biblioteca.model.Utilizador;
import uem.feng.biblioteca.model.dto.GestorUtilizadorDTO;
import uem.feng.biblioteca.repository.GestorRepository;
import uem.feng.biblioteca.repository.RoleRepository;
import uem.feng.biblioteca.service.GestorServices;
import uem.feng.biblioteca.service.UtilizadorService;

@RestController
@RequestMapping("/gestor")
public class GestorController {
	
	@Autowired
	GestorRepository gestorRepository;

//	@Autowired
//	private UtilizadorRepository utilizadorRepository;
	
	@Autowired
	private UtilizadorService utilizadorService;
	
	@Autowired
	private GestorServices gestorServices;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping
	public List<Gestor> listar(){
		return gestorRepository.findAll();		
	}
	
	@PostMapping
	public ResponseEntity <Gestor> criar (@RequestBody GestorUtilizadorDTO gestorUtilizadorDTO, HttpServletResponse response) {
		
		gestorServices.verificarConsistencia(gestorUtilizadorDTO.getGestor());
		Gestor gestor = gestorUtilizadorDTO.getGestor();
		Utilizador utilizador = gestorUtilizadorDTO.getUtilizador();
		 
		utilizadorService.verificarDuplicacaoDeEmailENome(utilizador);		
		
		//adicionar o utlizador as roles
		utilizador.getRoles().add(roleRepository.findByNome("ROLE_"+utilizador.getTipo()));
		
		Utilizador utilizadorSalvo = utilizadorService.saveUtilizador(utilizador);
		
		gestor.setUtilizador(utilizadorSalvo);
		
		Gestor gestorSalvo = gestorRepository.save(gestor);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, gestorSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(gestorSalvo);
		
	}
	
	
	
	
}
