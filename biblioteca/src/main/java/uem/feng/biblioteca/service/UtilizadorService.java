package uem.feng.biblioteca.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import uem.feng.biblioteca.model.Utilizador;
import uem.feng.biblioteca.repository.UtilizadorRepository;
import uem.feng.biblioteca.service.exception.NomeDeUltilizadorOuEmailExistenteException;

@Service
public class UtilizadorService implements UserDetailsService {
	@Autowired	
	UtilizadorRepository utilizadorRepository;
	
	
	private JavaMailSender mailSender;
	
	public UtilizadorService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	
	BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
	
	public void verificarDuplicacaoDeEmailENome(Utilizador utilizador) {
		List<Utilizador> listaDeUtilizadores = utilizadorRepository.findAll();
		
		for(int i=0; i<listaDeUtilizadores.size(); i++) {
			String email = listaDeUtilizadores.get(i).getEmail();
			String nomeUtilizador = listaDeUtilizadores.get(i).getNome();
			if(email.equalsIgnoreCase(utilizador.getEmail()) || nomeUtilizador.equalsIgnoreCase(utilizador.getNome())){
				throw new NomeDeUltilizadorOuEmailExistenteException();
			}
		}
	}
	
	public Utilizador saveUtilizador(Utilizador utilizador) {
		
		utilizador.setSenha(encoder.encode(utilizador.getSenha()));
		
		return utilizadorRepository.save(utilizador);
	}
	
	
	public void update_password(Utilizador user, String nova_password) {
		
		user.setSenha(nova_password);
		user.setPasswordtoken(null);
		this.saveUtilizador(user);
	}
	
    public void enviarEmail(String from, String to, String subject, String text) {
        
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
	        helper.setTo(to);
	        helper.setSubject(subject);
	        helper.setText(text, true); 
	        mailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilizador utilizador=utilizadorRepository.findByNome(username);
		
		
		if(utilizador==null) {
			throw new UsernameNotFoundException("");
		}
		
		Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
		utilizador.getRoles().forEach(role->{
			 authorities.add(new SimpleGrantedAuthority(role.getNome()));
		});
		
		return new org.springframework.security.core.userdetails.User(utilizador.getNome(),utilizador.getSenha(), authorities);
	}
	

}
