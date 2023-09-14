package uem.feng.biblioteca.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class RecursoCriadoEvent extends ApplicationEvent {
	

	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private Long codigo;
	private String nomeString;

	public RecursoCriadoEvent(Object source, HttpServletResponse response, Long codigo) {
		super(source);
		this.codigo=codigo;
		this.response=response;
	}
	
	public RecursoCriadoEvent(Object source, HttpServletResponse response, String nomeString) {
		super(source);
		this.nomeString=nomeString;
		this.response=response;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Long getCodigo() {
		return codigo;
	}
	
	public String getNomeString() {
		return nomeString;
	}

}
