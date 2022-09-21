package br.com.ats.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ats.classes.entities.Usuario;
import br.com.ats.dao.DaoUsuarioRepository;

@WebServlet(urlPatterns = { "/ServletUsuarioController" })
public class ServletUsuarioController extends ServletGenericUtil {

	private static final long serialVersionUID = 1L;

	private String urlPagCadastroUser = "principal/cadastro_usuario.jsp";
	private String urlPagError = "error.jsp";
	private DaoUsuarioRepository repository = new DaoUsuarioRepository();

	public ServletUsuarioController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {

				String idUser = request.getParameter("id");

				repository.deletar(Long.parseLong(idUser));

				List<Usuario> listaUsers = repository.consultaUserList(super.getUserLogado(request));

				request.setAttribute("listaUsers", listaUsers);

				request.setAttribute("msg", "Registro excluído com sucesso!");
				request.getRequestDispatcher("principal/cadastro_usuario.jsp").forward(request, response);
			} else

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarUserComAjax")) {

				String idUser = request.getParameter("id");

				repository.deletar(Long.parseLong(idUser));

				// response.getWriter().write("registro excluído com sucesso!");
			} else

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserComAjax")) {

				String nomeBusca = request.getParameter("nomeBusca");

				List<Usuario> dadosJsonUser = repository.buscarPorNome(nomeBusca, super.getUserLogado(request));

				ObjectMapper objectMapper = new ObjectMapper();

				String json = objectMapper.writeValueAsString(dadosJsonUser);

				response.getWriter().write(json);
			} else

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {

				String idUser = request.getParameter("id");

				Usuario objetoUsuario = repository.consultarPorId(idUser, super.getUserLogado(request));

				List<Usuario> listaUsers = repository.consultaUserList(super.getUserLogado(request));
				request.setAttribute("listaUsers", listaUsers);

				/* Seta a mensagem na tela */
				request.setAttribute("msg", "Registro em edição!");
				/* Seta os dados do objeto usuário na tela */
				request.setAttribute("objetoUsuario", objetoUsuario);
				/* Após salvar, a página redirecionada novamente para a página de cadastro */
				request.getRequestDispatcher(urlPagCadastroUser).forward(request, response);
			} else

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {

				List<Usuario> listaUsers = repository.consultaUserList(super.getUserLogado(request));

				/* Seta a mensagem na tela */
				request.setAttribute("msg", "Usuários carregados!");

				request.setAttribute("listaUsers", listaUsers);

				request.getRequestDispatcher(urlPagCadastroUser).forward(request, response);
			} else

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("viewUser")) {

				String idUser = request.getParameter("id");

				Usuario objetoUsuario = repository.consultarPorId(idUser, super.getUserLogado(request));

				/* Seta os dados do objeto usuário na tela */
				request.setAttribute("objetoUsuario", objetoUsuario);
			}

			else {

				List<Usuario> listaUsers = repository.consultaUserList(super.getUserLogado(request));
				request.setAttribute("listaUsers", listaUsers);

				request.getRequestDispatcher(urlPagCadastroUser).forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(urlPagError).forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String msgSucesso = "Operação realizada com sucesso!";
			String msgUpdate = "Registro atualizado com sucesso!";

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String cpf = request.getParameter("cpf");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String perfil = request.getParameter("perfil");

			Usuario objetoUsuario = new Usuario();
			objetoUsuario.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			objetoUsuario.setNome(nome);
			objetoUsuario.setCpf(cpf);
			objetoUsuario.setEmail(email);
			objetoUsuario.setLogin(login);
			objetoUsuario.setSenha(senha);
			objetoUsuario.setDataCadastro(Timestamp.valueOf(LocalDateTime.now()));
			objetoUsuario.setPerfil(perfil);

			String msg = null;

			if (repository.validarLogin(objetoUsuario.getLogin()) && objetoUsuario.getId() == null) {
				msg = "Já existe um usuário com o mesmo login, digite um login válido!";
			} else if (repository.validarCpf(objetoUsuario.getCpf()) && objetoUsuario.getId() == null) {
				msg = "Este CPF já estar cadastrado para outro usuário!";
			} else {

				if (objetoUsuario.isNovoUser()) {
					msg = msgSucesso;
				} else {
					msg = msgUpdate;
				}

				objetoUsuario = repository.salvar(objetoUsuario, super.getUserLogado(request));
			}

			List<Usuario> listaUsers = repository.consultaUserList(super.getUserLogado(request));
			request.setAttribute("listaUsers", listaUsers);

			/* Seta os dados do objeto usuário na tela */
			request.setAttribute("objetoUsuario", objetoUsuario);
			/* Seta a mensagem na tela */
			request.setAttribute("msg", msg);
			/* Após salvar, a página redirecionada novamente para a página de cadastro */
			request.getRequestDispatcher(urlPagCadastroUser).forward(request, response);
			;

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher(urlPagError).forward(request, response);
		}
	}

}
