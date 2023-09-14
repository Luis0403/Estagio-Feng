package uem.feng.biblioteca.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import uem.feng.biblioteca.filter.CustomerAuthenticationFilter;
import uem.feng.biblioteca.filter.CustomerAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends  WebSecurityConfigurerAdapter{
	
	@Autowired 
	private UserDetailsService userDetails;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetails).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CustomerAuthenticationFilter customerAuthenticationFilter =new CustomerAuthenticationFilter(authenticationManagerBean());
        customerAuthenticationFilter.setFilterProcessesUrl("/biblioteca/login");
		http.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests().antMatchers("/biblioteca/login","/utilizadores/refreshToken").permitAll()
			.and()
			.authorizeRequests().antMatchers(HttpMethod.GET,"/livro/**").permitAll()
			.and()
			//restricoes para metodos gets
			.authorizeRequests().antMatchers(HttpMethod.GET,"/estudante/**","/professor/**").hasAnyAuthority("ROLE_GESTOR","ROLE_ADMIN")
			.and()
			.authorizeRequests().antMatchers(HttpMethod.GET,"/gestor/**").hasAnyAuthority("ROLE_ADMIN")
			
			//restricoes para post
			.and()
			.authorizeRequests().antMatchers(HttpMethod.POST,"/estudante/**").permitAll()
			.and()
			.authorizeRequests().antMatchers(HttpMethod.POST,"/livro/**").hasAnyAuthority("ROLE_PROFESSOR","ROLE_GESTOR","ROLE_ADMIN")
			.and()
			.authorizeRequests().antMatchers(HttpMethod.POST,"/gestor/**").hasAnyAuthority("ROLE_ADMIN")
			.and()
			.authorizeRequests().antMatchers(HttpMethod.POST,"/professor/**").hasAnyAuthority("ROLE_GESTOR","ROLE_ADMIN")
			.and()
			//restricoes para put
			.authorizeRequests().antMatchers(HttpMethod.PUT,"/estudante/**").hasAnyAuthority("ROLE_GESTOR","ROLE_ESTUDANTE")
			.and()
			.authorizeRequests().antMatchers(HttpMethod.PUT,"/professor/**").hasAnyAuthority("ROLE_GESTOR","ROLE_PROFESSOR")
			.and()
			.authorizeRequests().antMatchers(HttpMethod.PUT,"/gestor/**").hasAnyAuthority("ROLE_ADMIN")
			.and()
			.authorizeRequests().antMatchers(HttpMethod.PUT,"/livro/**").hasAnyAuthority("ROLE_GESTOR","ROLE_ADMIN")
			.and()
			//restricoes para delete
			.authorizeRequests().antMatchers(HttpMethod.DELETE,"/estudante/**","/professor/**"/*,"/utilizadores/**"*/,"/livro/**").hasAnyAuthority("ROLE_GESTOR","ROLE_ADMIN")
			.and()
			.authorizeRequests().antMatchers(HttpMethod.DELETE,"/gestor/**").hasAnyAuthority("ROLE_ADMIN")
			.and()
			.addFilter(customerAuthenticationFilter)
			.addFilterBefore(new CustomerAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
	}
	
	





}
