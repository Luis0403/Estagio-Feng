package uem.feng.biblioteca.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import uem.feng.biblioteca.model.Professor;
import uem.feng.biblioteca.repository.ProfessorRepository;

@Service
public class ProfessorService {

	
	@Autowired
	private ProfessorRepository professorRepository;
	
	public Professor actualizar(long codigo, Professor professor) {
		Professor professorSalvo=buscarPeloCodigo(codigo);
		BeanUtils.copyProperties(professor, professorSalvo, "codigo");
		return professorRepository.save(professorSalvo);
	}
	
	public Professor buscarPeloCodigo(long codigo) {
		Professor professorSalvo=professorRepository.findByCodigo(codigo);
		if(professorSalvo==null) {
			throw new EmptyResultDataAccessException(0);
		}
		return professorSalvo;
	}
	
	public void verificarConsistencia(Professor professor) {
		String nome =professor.getNome();
		String numeroBi = professor.getNumeroBi();
		int celular = professor.getCelular();
		String area_de_ensino =professor.getAreaDeEnsino();
		String instituicao_de_ensino =professor.getInstituicaoDeEnsino();
		
		if(nome==null || numeroBi==null || celular==0 ||
				instituicao_de_ensino==null || area_de_ensino==null
				|| instituicao_de_ensino==null) {
			throw new DataIntegrityViolationException(""); 
		}
	}
	
	
}

