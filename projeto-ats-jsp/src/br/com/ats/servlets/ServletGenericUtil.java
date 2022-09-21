package br.com.ats.servlets;

import java.io.Serializable;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.ats.dao.DaoUsuarioRepository;

public class ServletGenericUtil extends HttpServlet implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private DaoUsuarioRepository repository = new DaoUsuarioRepository();
	
	
	public Long getUserLogado(HttpServletRequest request) throws Exception {
		
		HttpSession sessao = request.getSession();
		
		/*Pegando o objeto usu�rio que est� logado na sess�o*/
		String usuarioLogado = (String) sessao.getAttribute("usuario");
		
		return repository.buscarUsuarioLogado(usuarioLogado).getId();
	}
}
