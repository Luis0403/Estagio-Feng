package uem.feng.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uem.feng.biblioteca.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

	Livro findByCodigo(Long codigo);
	
	List<Livro> findByTitulo(String titulo);
	
	
	
	@Query(value="select * from livro where titulo like %:titulo%", nativeQuery=true)
	List<Livro> findByTituloIsContaining(String titulo);
	
	@Query(value="select * from livro where autor like %:autor%", nativeQuery=true)
	List<Livro> findByAutor(String autor);
	
	
	@Query(value="select * from livro where area_de_conhecimento like %:area_de_conhecimento% ", nativeQuery=true)
	List<Livro> findByAreaDeConhecimento(String area_de_conhecimento);
	
	
	

}
