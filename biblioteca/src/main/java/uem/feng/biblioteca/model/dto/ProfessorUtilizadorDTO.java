package uem.feng.biblioteca.model.dto;

import uem.feng.biblioteca.model.Professor;
import uem.feng.biblioteca.model.Utilizador;

public class ProfessorUtilizadorDTO {

	private Professor professor;
	
	private Utilizador utilizador;

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}
	
	
}
