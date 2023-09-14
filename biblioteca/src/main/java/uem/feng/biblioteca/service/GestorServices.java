package uem.feng.biblioteca.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import uem.feng.biblioteca.model.Gestor;


@Service
public class GestorServices {

	public void verificarConsistencia(Gestor gestor) {
		String nome =gestor.getNome();
		String numeroBi = gestor.getNumeroBi();
		int celular = gestor.getCelular();
		
		if(nome==null || numeroBi==null || celular==0 ) {
			throw new DataIntegrityViolationException(""); 
		}
	}
}
