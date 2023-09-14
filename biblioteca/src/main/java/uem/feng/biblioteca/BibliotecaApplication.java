package uem.feng.biblioteca;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BibliotecaApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);	
//		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
//		System.err.println(passwordEncoder.encode("1234"));
	}
	
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
