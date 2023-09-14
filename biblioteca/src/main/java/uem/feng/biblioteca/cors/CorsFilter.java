package uem.feng.biblioteca.cors;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

	//private String origemPermitida="http://locahost:2400/";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse)response;
		
		resp.setHeader("Access-Control-Allow-Origin",req.getHeader("Origin"));
		resp.setHeader("Access-Control-Allow-Credentials","true");
		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
		resp.setHeader("Access-Control-Allow-Headers", "*");
		//Content-Type, Content-Length, Host, Accept
		resp.setHeader("Access-Control-Max-Age", "3600");
		
		resp.setStatus(HttpServletResponse.SC_OK);
		
		if(!"OPTIONS".equals(req.getMethod())) {
			chain.doFilter(request, response);
		}
			
		
		
	}

}


