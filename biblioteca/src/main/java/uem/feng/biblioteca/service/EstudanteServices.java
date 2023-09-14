package uem.feng.biblioteca.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import uem.feng.biblioteca.model.Estudante;
import uem.feng.biblioteca.repository.EstudanteRepository;

@Service
public class EstudanteServices {
	
	@Autowired
	EstudanteRepository estudanteRepository;
	
	
	public Estudante actualizarEstudante(Long codigo, Estudante estudante) {
		
		Estudante findEstudante=buscarPeloCodigo(codigo);
		BeanUtils.copyProperties(estudante, findEstudante, "codigo");
		return estudanteRepository.save(findEstudante);
	}
	
	
	public Estudante buscarPeloCodigo(Long codigo) {
		Estudante estudante=estudanteRepository.findByCodigo(codigo);
		
		if(estudante==null) {
			throw new EmptyResultDataAccessException(0);
		}
		return estudante;
	}
	
	public void verificarConsistencia(Estudante estudante) {
		String nome =estudante.getNome();
		String numeroBi = estudante.getNumeroBi();
		int celular = estudante.getCelular();
		String instituicao_de_ensino =estudante.getInstituicao_de_ensino();
		String curso =estudante.getCurso();
		
		if(nome==null ||  numeroBi==null || celular==0 ||
				instituicao_de_ensino==null ||curso==null) {
			throw new DataIntegrityViolationException(""); 
		}
	}


}
