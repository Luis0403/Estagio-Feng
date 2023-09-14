package uem.feng.biblioteca.model.dto;

import uem.feng.biblioteca.model.Estudante;
import uem.feng.biblioteca.model.Utilizador;

public class EstudanteUtilizadorDTO {
  private Estudante estudante;
  private Utilizador utilizador;
  
  
public Estudante getEstudante() {
	return estudante;
}
public void setEstudante(Estudante estudante) {
	this.estudante = estudante;
}
public Utilizador getUtilizador() {
	return utilizador;
}
public void setUtilizador(Utilizador utilizador) {
	this.utilizador = utilizador;
}
  
}
