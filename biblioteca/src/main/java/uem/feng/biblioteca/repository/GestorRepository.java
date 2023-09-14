package uem.feng.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uem.feng.biblioteca.model.Gestor;

public interface GestorRepository extends JpaRepository<Gestor, Long> {
	
	Gestor findByCodigo(long codigo);
	
}
