package uem.feng.biblioteca.exceptionHandler;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uem.feng.biblioteca.service.exception.NomeDeUltilizadorOuEmailExistenteException;
import uem.feng.biblioteca.service.exception.UtilizadorInautorizadoException;


@ControllerAdvice
public class BibliotacaExeptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String mensagemDesevolvedor = ex.getCause() !=null? ex.getCause().toString(): ex.toString();
		String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		
		List <Erro> erros  = Arrays.asList(new Erro (mensagemUsuario, mensagemDesevolvedor));
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntimeException(RuntimeException ex, WebRequest request) {

		String mensagemDesevolvedor = ex.toString();
		List <Erro> erros  = Arrays.asList(new Erro (null, mensagemDesevolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
	}
	
	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<?> hendleFileNotFoundException(FileNotFoundException ex, WebRequest request){
		String mensagemDesevolvedor = ex.toString();
		String mensagemUsuario = messageSource.getMessage("livro-inexistente", null, LocaleContextHolder.getLocale());
		List <Erro> erros  = Arrays.asList(new Erro (mensagemUsuario, mensagemDesevolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(FileAlreadyExistsException.class)
	public ResponseEntity<?> hendleFileAlreadyExistsException(FileAlreadyExistsException ex, WebRequest request){
		String mensagemDesevolvedor = ex.toString();
		String mensagemUsuario = "O livro que deseja Adicionar Ja Existe";//messageSource.getMessage("livro-existente", null, LocaleContextHolder.getLocale());
		List <Erro> erros  = Arrays.asList(new Erro (mensagemUsuario, mensagemDesevolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List <Erro> erros  = criarListaDeErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({UtilizadorInautorizadoException.class})
	public ResponseEntity<Object> hendlerUtilizadorInautorizadoException(UtilizadorInautorizadoException ex, WebRequest request) {
		
		String mensagemDesevolvedor = ex.toString();
		String mensagemUsuario = messageSource.getMessage("utilizador-inautorizado", null, LocaleContextHolder.getLocale());
		List <Erro> erros  = Arrays.asList(new Erro (mensagemUsuario, mensagemDesevolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
	}
	
	@ExceptionHandler({NomeDeUltilizadorOuEmailExistenteException.class})
	public ResponseEntity<Object> hendlerNomeDeUltilizadorOuEmailExistenteException(NomeDeUltilizadorOuEmailExistenteException ex, WebRequest request) {
		
		String mensagemDesevolvedor = ex.toString();
		String mensagemUsuario = messageSource.getMessage("utilizador-ou-email-existente", null, LocaleContextHolder.getLocale());
		List <Erro> erros  = Arrays.asList(new Erro (mensagemUsuario, mensagemDesevolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<?> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		String mensagemDesevolvedor = ex.toString();
		String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		List <Erro> erros  = Arrays.asList(new Erro (mensagemUsuario, mensagemDesevolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
		
		String mensagemDesevolvedor = ex.toString();
		String mensagemUsuario = messageSource.getMessage("mensagem-invalida-campoNulo", null, LocaleContextHolder.getLocale());
		List <Erro> erros  = Arrays.asList(new Erro (mensagemUsuario, mensagemDesevolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> handleNullPointerException(DataIntegrityViolationException ex, WebRequest request){
		String mensagemDesevolvedor = ex.toString();
		String mensagemUsuario = messageSource.getMessage("mensagem-invalida-campoNulo", null, LocaleContextHolder.getLocale());
		List <Erro> erros  = Arrays.asList(new Erro (mensagemUsuario, mensagemDesevolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleNullPointerException(NullPointerException ex, WebRequest request){
		
		String mensagemDesevolvedor = ex.toString();
		String mensagemUsuario = messageSource.getMessage("mensagem.nula", null, LocaleContextHolder.getLocale());
		List <Erro> erros  = Arrays.asList(new Erro (mensagemUsuario, mensagemDesevolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		
	}
	
	private List<Erro> criarListaDeErros(BindingResult bindingResult){
		List<Erro> erros = new ArrayList<>();
		
		for(FieldError fieldError:  bindingResult.getFieldErrors()) {
			String mensagemDesevolvedor = bindingResult.toString();
			String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			erros.add(new Erro (mensagemUsuario, mensagemDesevolvedor));
		}
		return erros;
		
	}
	
	public static class Erro{
		
		private String mensagemUsuario;
		private String mensagemDesevolvedor;
		public Erro(String mensagemUsuario, String mensagemDesevolvedor) {
			
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesevolvedor = mensagemDesevolvedor;
		}
		public String getMensagemUsuario() {
			return mensagemUsuario;
		}
		public void setMensagemUsuario(String mensagemUsuario) {
			this.mensagemUsuario = mensagemUsuario;
		}
		public String getMensagemDesevolvedor() {
			return mensagemDesevolvedor;
		}
		public void setMensagemDesevolvedor(String mensagemDesevolvedor) {
			this.mensagemDesevolvedor = mensagemDesevolvedor;
		}		
	}

	

}
