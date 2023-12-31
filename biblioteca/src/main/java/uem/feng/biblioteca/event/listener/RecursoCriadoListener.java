package uem.feng.biblioteca.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import uem.feng.biblioteca.event.RecursoCriadoEvent;

public class RecursoCriadoListener  implements ApplicationListener<RecursoCriadoEvent> {

	@Override
	public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {
		HttpServletResponse response= recursoCriadoEvent.getResponse();
		Long codigo=recursoCriadoEvent.getCodigo();
		
		URI uri =ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(codigo).toUri();
		
		response.setHeader("Location", uri.toASCIIString());
	}
}