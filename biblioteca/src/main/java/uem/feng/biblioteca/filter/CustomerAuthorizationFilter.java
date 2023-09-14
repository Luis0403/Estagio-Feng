package uem.feng.biblioteca.filter;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import uem.feng.biblioteca.model.Role;
import uem.feng.biblioteca.model.Utilizador;
import uem.feng.biblioteca.repository.UtilizadorRepository;

public class CustomerAuthorizationFilter extends OncePerRequestFilter {
	
	public CustomerAuthorizationFilter() {}
	


	@Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
		
        if(request.getServletPath().equals("/biblioteca/login")  || request.getServletPath().equals("/utilizadores/refreshToken")){
           
            filterChain.doFilter(request, response);
        }
        else {
            String authorizationHeader=request.getHeader("authorization");

            if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer")){
                try {
                    String token=authorizationHeader.substring("Bearer ".length());
                  
                    Algorithm algorithm=Algorithm.HMAC256("secrete".getBytes()); 
                    JWTVerifier verifier=JWT.require(algorithm).build();
                    DecodedJWT decodedJWT=verifier.verify(token); 
                    
                    String username=decodedJWT.getSubject();
                    String [] roles=decodedJWT.getClaim("roles").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
                    
                    for (String role:roles) {
                    	authorities.add(new SimpleGrantedAuthority(role));
					}
                    
                    UsernamePasswordAuthenticationToken authenticationToken=
                            new UsernamePasswordAuthenticationToken(username,null,authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                    
                } catch (JWTVerificationException  e) {
                	
                    Map<String,String> erros=new HashMap<>();
                    erros.put("error_message: ",e.getMessage());
                    
                    new ObjectMapper().writeValue(response.getOutputStream(), erros);
                }
            }

            else {
                filterChain.doFilter(request, response);
            }
        }
    }
	
	
	public void refreshToken(HttpServletRequest request,HttpServletResponse response, UtilizadorRepository utilizadorRepository) throws  IOException {
		
		
		 if(request.getCookies()!=null){
			 
			   try {
	                 String refresh_token=pegarCookie(request);
	                 Algorithm algorithm=Algorithm.HMAC256("secrete".getBytes()); 
	                 JWTVerifier verifier=JWT.require(algorithm).build();
	                 DecodedJWT decodedJWT=verifier.verify(refresh_token); 
	                 String username=decodedJWT.getSubject();
	                 Utilizador utilizador=utilizadorRepository.findByNome(username);
	                 
	                 String access_token=JWT.create()
	         				.withSubject(utilizador.getNome())
	         				.withExpiresAt(new Date(System.currentTimeMillis()+1*60*1000))
	         				.withIssuer(request.getRequestURL().toString())
	         				.withClaim("roles", utilizador.getRoles().stream().map(Role::getNome).collect(Collectors.toList()))
	        				.sign(algorithm);
	                 
	                 Map<String,String> tokensMap=new HashMap<>();
	                 tokensMap.put("access_token", access_token);
	                 response.setContentType("aplication/json");
	                 new ObjectMapper().writeValue(response.getOutputStream(), tokensMap);
	         	
	                 
	             } catch (JWTVerificationException  e) {
	             	
	                 Map<String,String> erros=new HashMap<>();
	                 erros.put("error_message: ",e.getMessage());
	                 
	                 new ObjectMapper().writeValue(response.getOutputStream(), erros);
	             }
		 }
		 else {
			  throw new RuntimeException();
		 }
	}



	private String pegarCookie(HttpServletRequest request) {
		for(Cookie cookie: request.getCookies()) { 
			if(cookie.getName().equals("refresh_token")) 
				return cookie.getValue();
		}
		return null;
	} 
		 
}
