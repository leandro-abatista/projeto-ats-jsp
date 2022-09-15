package br.com.ats.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ats.classes.entities.Usuario;
import br.com.ats.dao.DaoUsuarioRepository;

@WebServlet(urlPatterns = {"/ServletUsuarioController"})
public class ServletUsuarioController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private String urlPagCadastroUser = "principal/cadastro_usuario.jsp";
	private String urlPagError = "/error.jsp";
	private DaoUsuarioRepository repository = new DaoUsuarioRepository();

	public ServletUsuarioController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			
			String msgSucesso = "Opera��o realizada com sucesso!";
			String msgUpdate = "Registro atualizado com sucesso!";
			String msgAlerta = "J� existe um usu�rio com o mesmo login, digite um login v�lido!";
	
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String cpf = request.getParameter("cpf");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			
			Usuario objetoUsuario = new Usuario();
			objetoUsuario.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			objetoUsuario.setNome(nome);
			objetoUsuario.setCpf(cpf);
			objetoUsuario.setEmail(email);
			objetoUsuario.setLogin(login);
			objetoUsuario.setSenha(senha);
			objetoUsuario.setDataCadastro(Timestamp.valueOf(LocalDateTime.now()));
			
			if (repository.validarLogin(objetoUsuario.getLogin()) && objetoUsuario.getId() == null) {
				request.setAttribute("msg", msgAlerta);
			} else {

				if (objetoUsuario.isNovoUser()) {
					request.setAttribute("msg", msgSucesso);
				} else {
					request.setAttribute("msg", msgUpdate);
				}
				objetoUsuario = repository.salvar(objetoUsuario);
			}
			
			/*Ap�s salvar, a p�gina redirecionada novamente para a p�gina de cadastro*/
			RequestDispatcher redireciona = request.getRequestDispatcher(urlPagCadastroUser);
			request.setAttribute("objetoUsuario", objetoUsuario);
			redireciona.forward(request, response);
		
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher(urlPagError).forward(request, response);
		}
	}

}
