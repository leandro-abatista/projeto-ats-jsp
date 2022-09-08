package br.com.ats.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ats.conexao.SingleConnection;

public class FilterAutenticacao extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;
	
	private static Connection connection;
	private String urlPagLogin = "/principal/ServletLogin";
	private String urlPagInicial = "/index.jsp";
	private String urlPagErro = "error.jsp";

	/**
	 * Construtor padr�o da classe
	 */
	public FilterAutenticacao() {
	}
	
	/**
	 * Encerra/mata os processos de conex�o quando o servidor � parado.
	 */
	@Override
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest requisicao = request;
			HttpSession sessao = requisicao.getSession();
			
			/*Pegando o objeto usu�rio que est� logado na sess�o*/
			String usuarioLogado = (String) sessao.getAttribute("usuario");
			
			/*Pegando a url que est� sendo acessada*/
			String urlParaAutenticar = requisicao.getServletPath();
			
			if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase(urlPagLogin)) {/*N�o est� �ogado na sess�o*/
				request.getRequestDispatcher(urlPagInicial + "?url=" + urlParaAutenticar).forward(request, response);
				request.setAttribute("msg", "Para acesso ao sistema, informe login e senha!");
				return;/*Caso o login seja null, ele redireciona novamente para a p�gina de autentica��o*/
			} else {
				chain.doFilter(request, response);
			}
			
			connection.commit();/*Se deu tudo certo, commita as altera��es*/
			
		} catch (SQLException e) {
			e.printStackTrace();
			request.getRequestDispatcher(urlPagErro).forward(request, response);
		}
		
	}
	
	/**
	 * Inicia os processos ou recursos quando o servidor sobe o projeto.
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		/*Assim que inicia o sistema, tamb�m � criado uma conex�o com o banco de dados*/
		connection = SingleConnection.getConnection();
	}

}
