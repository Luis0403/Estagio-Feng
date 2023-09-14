package uem.feng.biblioteca.model.dto;

import org.springframework.web.multipart.MultipartFile;
import uem.feng.biblioteca.model.Livro;

public class LivroFileDTO {
	private Livro livro;
	private MultipartFile file;
	
	public Livro getLivro() {
		return livro;
	}
	public void setEstudante(Livro livro) {
		this.livro = livro;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setUtilizador(MultipartFile file ) {
		this.file = file;
	}
}


