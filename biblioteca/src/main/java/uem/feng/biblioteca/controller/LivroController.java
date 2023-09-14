package uem.feng.biblioteca.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import uem.feng.biblioteca.event.RecursoCriadoEvent;
import uem.feng.biblioteca.model.Livro;
import uem.feng.biblioteca.model.Utilizador;
import uem.feng.biblioteca.repository.LivroRepository;
import uem.feng.biblioteca.repository.UtilizadorRepository;
import uem.feng.biblioteca.service.LivrosServicos;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
@RequestMapping("/livro")
public class LivroController{

	//variavel do local onde serao guardados e carregados os livros
	public static  final String DIRECTORY=System.getProperty("user.dir")+"\\uploads"; //TODO: quando estiver temos de alterar para user.home onde serao guardados os arquivos
	

	@Autowired
	LivroRepository livroRepository;
	
	@Autowired
	UtilizadorRepository utilizadorRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private LivrosServicos livrosServicos;
	
	
	@GetMapping
	public List<Livro> listar(){
		return livroRepository.findAll();
	} 
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@GetMapping("/{titulo}&{autor}&{areaDeConhecimento}")
	public ResponseEntity<?> buscar(@PathVariable String titulo,@PathVariable String autor,@PathVariable String areaDeConhecimento){
		
		List <Livro> livro_titulo =this.livroRepository.findByTituloIsContaining(titulo);
		List <Livro> livro_autor=livroRepository.findByAutor(autor);
		List <Livro> livro_areaDeConhecimento=livroRepository.findByAreaDeConhecimento(areaDeConhecimento);
		
		//essas todas condicoes vao permitir retorno de unicos objectos e tambem vai ultrapassar o erro java heap space quando no caso da lista estiver cheia de mais com os mesmos elementos
		if(!livro_titulo.isEmpty()) {
				livro_autor.forEach(books->{
				for(int i=0; i<livro_titulo.size(); i++) {
					if(books!=livro_titulo.get(i)) {
						livro_titulo.add(books);
					}
				}
			});
			
			
			livro_areaDeConhecimento.forEach(books->{
				for(int i=0; i<livro_titulo.size(); i++) {
					if(books!=livro_titulo.get(i)) {
						livro_titulo.add(books);
					}
				}
			});
			
			return ResponseEntity.status(HttpStatus.OK).body(livro_titulo);
		}
		else
			if(!livro_autor.isEmpty()) {
				System.err.print("preenchido");
				livro_areaDeConhecimento.forEach(books->{
					for(int i=0; i<livro_autor.size(); i++) {
						if(books!=livro_autor.get(i)) {
							livro_autor.add(books);
						}
					}
				});
				return ResponseEntity.status(HttpStatus.OK).body(livro_autor);
			}
		
		return ResponseEntity.status(HttpStatus.OK).body(livro_areaDeConhecimento);
		
	} 
	
	@PostMapping
	public ResponseEntity<?> salvar(@Valid @RequestBody Livro livro,   HttpServletResponse response) throws IOException{
		
		Utilizador user=utilizadorRepository.findByNome(livro.getUtilizador().getNome());
		
		if(user==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		

		List<Livro> books=livroRepository.findByTitulo(livro.getTitulo());
		for(int i=0; i<books.size(); i++) {
			if(livro.getTitulo().equalsIgnoreCase(books.get(i).getTitulo())) 
				return ResponseEntity.status(HttpStatus.CONFLICT).build();		
		}
		
		livro.setUtilizador(user);
		Livro livroSalvo=this.livroRepository.save(livro);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, livroSalvo.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(livroSalvo);

	}
	
	@PutMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> actualizar(@PathVariable ("codigo") Long codigo, @RequestBody Livro livro){
		Livro livroSalvo = livroRepository.findByCodigo(codigo);
		BeanUtils.copyProperties(livro, livroSalvo,"codigo");
		livroRepository.save(livroSalvo);
		return ResponseEntity.ok(livroSalvo);
	}
	
	
	@GetMapping("/por_user/{nome}")
	public ResponseEntity<?> findBookbyUser(@PathVariable String nome){
		
		Utilizador user=utilizadorRepository.findByNome(nome);
		if(user==null) {
			throw new UsernameNotFoundException("Nao existe utilizador com esse username: "+nome);
		}
		
		List<Livro> livro = new ArrayList<>();
		
		for(Livro livros:livroRepository.findAll() ) {
			if(livros.getUtilizador().getNome().equalsIgnoreCase(nome)) {
				livro.add(livros);
			}
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(livro);
	}
	
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
 	public void remover(@PathVariable ("codigo") Livro livro) throws  FileNotFoundException {
		
		//Livro book=this.livroRepository.findByCodigo(livro.getCodigo());
		
		if(!this.livrosServicos.verificarApagar(livro.getTitulo())) {
			throw new FileNotFoundException();
		}
			
		livrosServicos.apagarLivro(livro.getTitulo());
		livroRepository.delete(livro);	
	}

	//definir o metodo para upload
	@PostMapping("/upload")
	public ResponseEntity<?> uploadLivro(@RequestParam("file") MultipartFile file) throws IOException {

		String filename= StringUtils.cleanPath(file.getOriginalFilename());
		
			
		
		//verificar o tipo de arquivo
		if(!StringUtils.endsWithIgnoreCase(filename, "pdf")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato nao suportado, Apenas sao aceites arquivos com extensao .pdf");
		}
		
		
		//publisher.publishEvent(new RecursoCriadoEvent(this, response, file.getOriginalFilename()));
		
		if(this.livrosServicos.gravar_FileLivro(file)) {
			throw new FileAlreadyExistsException(filename);
		}

		return ResponseEntity.ok().build();
		
	}

	//definir o metodo para download
	@RequestMapping("/download")
	public ResponseEntity<Resource> download(HttpServletRequest request) throws IOException {
		
		String filename=request.getParameter("filename");

		Path filePath=Paths.get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
		if(!Files.exists(filePath)){
			throw  new FileNotFoundException("Livro nao encontrado");
		}

		Resource resource=new UrlResource(filePath.toUri());
		HttpHeaders headers=new HttpHeaders();
		headers.add("File-Name",filename);
		headers.add(CONTENT_DISPOSITION,"attachment;File-Name="+filename);

		return  ResponseEntity
				.ok()
				.contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
				.headers(headers)
				.body(resource);
	}

	

}
