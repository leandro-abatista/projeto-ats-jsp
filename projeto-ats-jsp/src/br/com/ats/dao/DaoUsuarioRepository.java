package br.com.ats.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ats.classes.entities.Usuario;
import br.com.ats.conexao.SingleConnection;

public class DaoUsuarioRepository {

	private Connection connection;
	
	public DaoUsuarioRepository() {
		connection = SingleConnection.getConnection();
	}
	
	public Usuario salvar(Usuario objeto, Long userLogado) throws Exception {
		
		if (objeto.isNovoUser()) {
			
			String sql = "INSERT INTO public.usuario(" 
					+ "   nome, cpf, email, login, senha, data_cadastro, usuario_id, perfil)" 
					+ "   VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, objeto.getNome());
			statement.setString(2, objeto.getCpf());
			statement.setString(3, objeto.getEmail());
			statement.setString(4, objeto.getLogin());
			statement.setString(5, objeto.getSenha());
			statement.setTimestamp(6, objeto.getDataCadastro());
			statement.setLong(7, userLogado);
			statement.setString(8, objeto.getPerfil());
			
			statement.execute();
			connection.commit();
			
		} else {
			
			String sql = "UPDATE public.usuario" + 
					"   SET nome=?, cpf=?, email=?, login=?, senha=?, perfil=?" + 
					" WHERE id = " + objeto.getId() + "";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, objeto.getNome());
			statement.setString(2, objeto.getCpf());
			statement.setString(3, objeto.getEmail());
			statement.setString(4, objeto.getLogin());
			statement.setString(5, objeto.getSenha());
			statement.setString(6, objeto.getPerfil());
			
			statement.executeUpdate();
			connection.commit();
		}
		
		return this.consultar(objeto.getLogin(), userLogado);
	}
	
	public Usuario buscarUsuarioLogado(String login) throws Exception {
		
		Usuario objetoUsuario = new Usuario();
		
		String sql = "SELECT "
				+ "id, nome, cpf, email, login, senha, data_cadastro, useradmin, usuario_id, perfil " 
				+ " FROM public.usuario"
				+ " WHERE UPPER(login) = UPPER(?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "" + login + "");
		
		ResultSet resultado = statement.executeQuery();
		if (resultado.next()) {/*Se tiver algum resultado*/
			objetoUsuario.setId(resultado.getLong("id"));
			objetoUsuario.setNome(resultado.getString("nome"));
			objetoUsuario.setCpf(resultado.getString("cpf"));
			objetoUsuario.setEmail(resultado.getString("email"));
			objetoUsuario.setLogin(resultado.getString("login"));
			objetoUsuario.setSenha(resultado.getString("senha"));
			objetoUsuario.setPerfil(resultado.getString("perfil"));
		}
		
		return objetoUsuario;
	}
	
	public Usuario consultar(String login) throws Exception {
		
		Usuario objetoUsuario = new Usuario();
		
		String sql = "SELECT "
				+ "id, nome, cpf, email, login, senha, data_cadastro, useradmin, usuario_id, perfil" 
				+ " FROM public.usuario"
				+ " WHERE UPPER(login) = UPPER(?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "" + login + "");
		
		ResultSet resultado = statement.executeQuery();
		if (resultado.next()) {/*Se tiver algum resultado*/
			objetoUsuario.setId(resultado.getLong("id"));
			objetoUsuario.setNome(resultado.getString("nome"));
			objetoUsuario.setCpf(resultado.getString("cpf"));
			objetoUsuario.setEmail(resultado.getString("email"));
			objetoUsuario.setLogin(resultado.getString("login"));
			objetoUsuario.setSenha(resultado.getString("senha"));
			objetoUsuario.setUseradmin(resultado.getBoolean("useradmin"));
			objetoUsuario.setPerfil(resultado.getString("perfil"));
		}
		
		return objetoUsuario;
	}
	
	public Usuario consultar(String login, Long userLogado) throws Exception {
		
		Usuario objetoUsuario = new Usuario();
		
		String sql = "SELECT "
				+ " id, nome, cpf, email, login, senha, data_cadastro, useradmin, usuario_id, perfil" 
				+ " FROM public.usuario "
				+ " WHERE UPPER(login) = UPPER(?) "
				+ " AND useradmin is false "
				+ "	AND usuario_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, objetoUsuario.getLogin());
		statement.setLong(2, userLogado);
		
		ResultSet resultado = statement.executeQuery();
		if (resultado.next()) {/*Se tiver algum resultado*/
			objetoUsuario.setId(resultado.getLong("id"));
			objetoUsuario.setNome(resultado.getString("nome"));
			objetoUsuario.setCpf(resultado.getString("cpf"));
			objetoUsuario.setEmail(resultado.getString("email"));
			objetoUsuario.setLogin(resultado.getString("login"));
			objetoUsuario.setSenha(resultado.getString("senha"));
			objetoUsuario.setPerfil(resultado.getString("perfil"));
		}
		
		return objetoUsuario;
	}
	
	public Usuario consultarPorId(String idUser, Long userLogado) throws Exception {
		
		Usuario objetoUsuario = new Usuario();
		
		String sql = "SELECT "
				+ "id, nome, cpf, email, login, senha, data_cadastro, useradmin, usuario_id, perfil" 
				+ "  FROM public.usuario "
				+ " WHERE id = ? "
				+ " AND useradmin is false "
				+ " AND usuario_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(idUser));
		statement.setLong(2, userLogado);
		
		ResultSet resultado = statement.executeQuery();
		if (resultado.next()) {/*Se tiver algum resultado*/
			objetoUsuario.setId(resultado.getLong("id"));
			objetoUsuario.setNome(resultado.getString("nome"));
			objetoUsuario.setCpf(resultado.getString("cpf"));
			objetoUsuario.setEmail(resultado.getString("email"));
			objetoUsuario.setLogin(resultado.getString("login"));
			objetoUsuario.setSenha(resultado.getString("senha"));
			objetoUsuario.setPerfil(resultado.getString("perfil"));
		}
		
		return objetoUsuario;
	}
	
	public List<Usuario> buscarPorNome(String nome, Long userLogado) throws Exception {
		
		List<Usuario> listaUsuarios = new ArrayList<>();
		
		String sql = "SELECT "
				+ "id, nome, cpf, email, login, senha, data_cadastro, useradmin, usuario_id, perfil" 
				+ "  FROM public.usuario "
				+ " WHERE UPPER(nome) LIKE UPPER(?) "
				+ " AND useradmin is false "
				+ " AND usuario_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nome + "%");
		statement.setLong(2, userLogado);
		
		ResultSet resultado = statement.executeQuery();
		while (resultado.next()) {/*Enquanto tiver resultados*/
			Usuario objetoUsuario = new Usuario();
			objetoUsuario.setId(resultado.getLong("id"));
			objetoUsuario.setNome(resultado.getString("nome"));
			objetoUsuario.setCpf(resultado.getString("cpf"));
			objetoUsuario.setEmail(resultado.getString("email"));
			objetoUsuario.setLogin(resultado.getString("login"));
			objetoUsuario.setDataCadastro(resultado.getTimestamp("data_cadastro"));
			objetoUsuario.setPerfil(resultado.getString("perfil"));
			//objetoUsuario.setSenha(resultado.getString("senha"));
			
			listaUsuarios.add(objetoUsuario);
		}
		
		return listaUsuarios;
	}
	
	public List<Usuario> consultaUserList(Long userLogado) throws Exception {
		
		List<Usuario> listaUsuarios = new ArrayList<>();
		
		String sql = "SELECT "
				+ " id, nome, cpf, email, login, senha, data_cadastro, useradmin, usuario_id, perfil" 
				+ " FROM public.usuario "
				+ " WHERE useradmin is false "
				+ " AND usuario_id = " + userLogado;
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		while (resultado.next()) {/*Enquanto tiver resultados*/
			Usuario objetoUsuario = new Usuario();
			objetoUsuario.setId(resultado.getLong("id"));
			objetoUsuario.setNome(resultado.getString("nome"));
			objetoUsuario.setCpf(resultado.getString("cpf"));
			objetoUsuario.setEmail(resultado.getString("email"));
			objetoUsuario.setLogin(resultado.getString("login"));
			objetoUsuario.setDataCadastro(resultado.getTimestamp("data_cadastro"));
			objetoUsuario.setPerfil(resultado.getString("perfil"));
			//objetoUsuario.setSenha(resultado.getString("senha"));
			
			listaUsuarios.add(objetoUsuario);
		}
		
		return listaUsuarios;
	}
	
	public boolean validarLogin(String login) throws Exception {
		
		String sql = "SELECT COUNT(1) > 0 AS existe "
				+ " FROM public.usuario" + 
				" WHERE UPPER(login) = UPPER(?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		
		ResultSet resultado = statement.executeQuery();
		if (resultado.next()) {/*Se tiver algum resultado*/
			return resultado.getBoolean("existe");
		}
		
		return false;
	}
	
	public boolean validarCpf(String cpf) throws Exception {
		
		String sql = "SELECT COUNT(1) > 0 AS existe "
				+ " FROM public.usuario" + 
				" WHERE cpf = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, cpf);
		
		ResultSet resultado = statement.executeQuery();
		if (resultado.next()) {/*Se tiver algum resultado*/
			return resultado.getBoolean("existe");
		}
		
		return false;
	}
	
	public void deletar(Long idObjetoUsuario) throws Exception {
		
		String sql = "DELETE FROM public.usuario" + 
				" WHERE id = ?"
				+ " AND useradmin is false";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, idObjetoUsuario);
		
		statement.executeUpdate();
		connection.commit();
	}
}
