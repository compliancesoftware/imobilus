package br.com.douglasfernandes.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.douglasfernandes.dataservices.entities.Perfil;

/**
 * Intercepta solicitações (requests) antes de entregar ao controller
 * @author douglas.f.filho
 *
 */
public class MainInterceptor extends HandlerInterceptorAdapter{

	private String[] permissions = new String[]{
			"/resources/",
			"/home",
			"esqueciMinhaSenha",
			"/login",
			"services",
			"/rest/"
	};	
	
	private boolean containsPermission(String uri){
		boolean contains = false;
		for(String p : permissions){
			if(uri.contains(p))
				contains = true;
		}
		return contains;
	}
	
	@Override
	  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object controller)
	 {
		  try{
			  String uri = request.getRequestURI();
		      
			  Perfil logado = (Perfil)request.getSession().getAttribute("logado");
			  
			  if(containsPermission(uri)){
				  return true;
			  }
			  
			  if(logado != null){
				  return true;
			  }
			  else{
				  response.sendRedirect("login");
				  return false;
			  }
		  }
		  catch(Exception e){
			  e.printStackTrace();
			  return false;
		  }
	 }

}
