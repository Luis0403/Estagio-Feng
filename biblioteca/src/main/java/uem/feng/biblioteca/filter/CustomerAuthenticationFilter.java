package uem.feng.biblioteca.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public CustomerAuthenticationFilter(AuthenticationManager authenticationManager) {
	
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, password);

		
		return authenticationManager.authenticate(authenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		
		User user=(User) authentication.getPrincipal();
		
		Algorithm algorithm=Algorithm.HMAC256("secrete".getBytes());
		
		String access_token=JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+1*60*1000))
				.withIssuer(request.getRequestURL().toString())
				.withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm);
	
        
		String refresh_token=JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+1440*60*1000))
				.withIssuer(request.getRequestURL().toString())
				.sign(algorithm);
		
		//adicionando o cookie no endereco do refresh token
		Cookie cookie=new Cookie("refresh_token", refresh_token);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(3600);
		cookie.setPath("/biblioteca/refreshToken");
		response.addCookie(cookie);
		
		Map<String,String> tokensMap=new HashMap<>();
        tokensMap.put("access_token", access_token);
        response.setContentType("aplication/json");
        new ObjectMapper().writeValue(response.getOutputStream(), tokensMap);
	}
}


