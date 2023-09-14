package uem.feng.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uem.feng.biblioteca.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByNome(String nome);
}
