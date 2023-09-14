package uem.feng.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uem.feng.biblioteca.model.Estudante;

public interface EstudanteRepository extends JpaRepository<Estudante,Long> {
		Estudante findByCodigo(Long codigo);
}
