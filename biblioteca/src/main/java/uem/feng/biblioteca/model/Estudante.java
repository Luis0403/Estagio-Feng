package uem.feng.biblioteca.model;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "estudante")
public class Estudante{
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigo;
	
	@NotNull
	private String nome;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Sexo sexo; 
	
	@Column(name = "numero_de_bi")
	private String numeroBi;
	
	private String foto;		
		
	@NotNull
	@Column(name = "data_de_nascimento")
	private LocalDate dataNascimento;
	
	@NotNull
	private int celular;
	
	@NotNull
	private String instituicao_de_ensino;
	
	@NotNull
	private String curso;	
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "codigo_utilizador")
	private  Utilizador utilizador;
	
	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}


	public String getInstituicao_de_ensino() {
		return instituicao_de_ensino;
	}

	public void setInstituicao_de_ensino(String instituicao_de_ensino) {
		this.instituicao_de_ensino = instituicao_de_ensino;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getNumeroBi() {
		return numeroBi;
	}

	public void setNumeroBi(String numeroBi) {
		this.numeroBi = numeroBi;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getCelular() {
		return celular;
	}

	public void setCelular(int celular) {
		this.celular = celular;
	}

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}

	
	
	
	
	
}
