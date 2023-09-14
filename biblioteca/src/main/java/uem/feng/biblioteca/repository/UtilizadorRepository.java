package uem.feng.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uem.feng.biblioteca.model.Utilizador;

public interface UtilizadorRepository extends JpaRepository<Utilizador,Long>{
	Utilizador findByCodigo(long codigo);
	Utilizador findByNome(String nome);
	Utilizador findByPasswordtoken(String passwordtoken);
	Utilizador findByEmail(String email);
}
