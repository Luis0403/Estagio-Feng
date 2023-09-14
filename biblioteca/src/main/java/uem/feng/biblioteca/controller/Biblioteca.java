package uem.feng.biblioteca.controller;

import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import net.bytebuddy.utility.RandomString;
import uem.feng.biblioteca.filter.CustomerAuthorizationFilter;
import uem.feng.biblioteca.model.PasswordRecover;
import uem.feng.biblioteca.model.Utilizador;
import uem.feng.biblioteca.repository.UtilizadorRepository;
import uem.feng.biblioteca.service.UtilizadorService;

@RestController
@RequestMapping("/biblioteca")
public class Biblioteca {
	
	@Autowired
	private UtilizadorRepository utilizadorRepository;
	
	@Autowired
	private UtilizadorService utilizadorService;
	
	
	private Utilizador utilizador;
	
	 ModelAndView modelAndView;
	
	public Biblioteca(){
		modelAndView = new ModelAndView();
	}

	@GetMapping("/refreshToken")
	public void refreshToken(HttpServletRequest request,HttpServletResponse response) throws IOException{
		System.err.println("Estou no refresh....");
		
		if(request.getCookies()==null) throw new NullPointerException("Inicie a sessao novamente, por favor!");
		CustomerAuthorizationFilter cust=new CustomerAuthorizationFilter();
		cust.refreshToken(request, response,utilizadorRepository);
	}
	
	@DeleteMapping("/logout")
	public void logOut( HttpServletResponse response) {
		
		Cookie cookie=new Cookie("refresh_token",null);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(0);
		cookie.setSecure(true);
		cookie.setPath("/biblioteca/refreshToken");
		response.addCookie(cookie);
		response.setStatus(HttpStatus.NO_CONTENT.value());
	}
	
	@PostMapping("/forgot_password")
	public void forgot_password(HttpServletRequest request) {
		String email=request.getParameter("email");
		
		Utilizador user=this.utilizadorRepository.findByEmail(email);
		
		if(user!=null) {
			//gerando um token
			String token=RandomString.make(50);
			user.setPasswordtoken(token);
			utilizadorRepository.save(user);
			
			
			String resetLink="http://localhost:8080/biblioteca/change_password_form?token="+token;
			String from="macuvelluis@gmail.com";
			String subject="link para resetar a sua password";
			String texto="<p>Ola</p>"+
			"<p>Voce requisitou a troca da sua password, clique no link abaixo para torcar.</p><br>"+
			"<a href="+resetLink+">Trocar a password</a><br>"+
			"<p>Igonre a mensagem caso ainda se lembra da sua password.</p>"+
			"<p>Obrigado!</p>";
			
			System.err.println(resetLink);
			
		this.utilizadorService.enviarEmail(from,email, subject, texto);
			
		}else {
			//lancar excepcao
		}
		
	}
	
	
	@GetMapping("/change_password_form")
	public ModelAndView loginTest (Model model, HttpServletRequest request ) {
		
	   
	    modelAndView.setViewName("formulario");
	    PasswordRecover recover=new PasswordRecover();
	    model.addAttribute("recover", recover);
	   
	    
	    if(request.getParameter("token")==null || request.getParameter("token")=="") {
	    	 modelAndView.setViewName("Erro");
	    	 return modelAndView;
	    }
	    
	    this.utilizador=this.utilizadorRepository.findByPasswordtoken(request.getParameter("token"));
	    
	    if(this.utilizador==null) {
	    	modelAndView.setViewName("Erro");
	    	 return modelAndView;
	    }
	    return modelAndView;
	}
	
	@PostMapping("/change_password")
	public ModelAndView submitForm(@ModelAttribute("user") PasswordRecover recover) {
		
		utilizadorService.update_password(utilizador, recover.getPassword());
		modelAndView.setViewName("Sucesso");
		return modelAndView;
	}
	
	
	@PutMapping("/change_passwordUpdate")
	public void update_password(HttpServletRequest request) {
		
		String password=request.getParameter("nova_password");
		String username=request.getParameter("username");
		
		Utilizador user=utilizadorRepository.findByNome(username);
		
		if(user!=null) {
			
			utilizadorService.update_password(user, password);
		}
		
	}
	
	@PostMapping("/sendEmail")
	public void sendEmail(HttpServletRequest request) {
		this.utilizadorService.enviarEmail("macuvelluis@gmail.com",request.getParameter("email") ,
				request.getParameter("subject"), request.getParameter("emailText"));
	}
}
