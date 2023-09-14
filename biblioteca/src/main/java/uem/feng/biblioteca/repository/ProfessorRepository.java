package uem.feng.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uem.feng.biblioteca.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{

	Professor findByCodigo(long codigo);
}
