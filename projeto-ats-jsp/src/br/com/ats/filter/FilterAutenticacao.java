package br.com.ats.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class FilterAutenticacao extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;
	
	public FilterAutenticacao() {
		
	}
	
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		try {
			
			HttpServletRequest requisicao = request;
			HttpSession sessao = requisicao.getSession();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
