package br.com.ats.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
			
			String msgSucesso = "Operação realizada com sucesso!";
			String msgUpdate = "Registro atualizado com sucesso!";
			String msgAlerta = "Já existe um usuário com o mesmo login, digite um login válido!";
	
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
			
			objetoUsuario = repository.salvar(objetoUsuario);
		
			/*Após salvar, a página redirecionada novamente para a página de cadastro*/
			request.setAttribute("msg", msgSucesso);
			request.setAttribute("objetoUsuario", objetoUsuario);
			request.getRequestDispatcher(urlPagCadastroUser).forward(request, response);
		
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher(urlPagError).forward(request, response);
		}
	}

}
