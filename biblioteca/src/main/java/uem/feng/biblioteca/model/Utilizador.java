package uem.feng.biblioteca.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "utilizador")
public class Utilizador {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String email;
	
	@NotNull
	private String senha;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoUtilizador tipo;
	
	private String passwordtoken;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Role> roles=new ArrayList<>(); 	
	

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public String getPasswordtoken() {
		return passwordtoken;
	}

	public void setPasswordtoken(String passwordtoken) {
		this.passwordtoken = passwordtoken;
	}

	//
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoUtilizador getTipo() {
		return tipo;
	}

	public void setTipo(TipoUtilizador tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilizador other = (Utilizador) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
	
	 
	
	
	

}
