package br.com.ats.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.ats.classes.entities.Usuario;
import br.com.ats.conexao.SingleConnection;

public class DaoUsuarioRepository {

	private Connection connection;
	
	public DaoUsuarioRepository() {
		connection = SingleConnection.getConnection();
	}
	
	public Usuario salvar(Usuario objeto) throws Exception {
		
		if (objeto.isNovoUser()) {
			
			String sql = "INSERT INTO public.usuario(" + 
					"    nome, cpf, email, login, senha, data_cadastro)" + 
					"    VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, objeto.getNome());
			statement.setString(2, objeto.getCpf());
			statement.setString(3, objeto.getEmail());
			statement.setString(4, objeto.getLogin());
			statement.setString(5, objeto.getSenha());
			statement.setTimestamp(6, objeto.getDataCadastro());
			
			statement.execute();
			connection.commit();
			
		} else {
			
			String sql = "UPDATE public.usuario" + 
					"   SET nome=?, cpf=?, email=?, login=?, senha=?" + 
					" WHERE id = " + objeto.getId() + "";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, objeto.getNome());
			statement.setString(2, objeto.getCpf());
			statement.setString(3, objeto.getEmail());
			statement.setString(4, objeto.getLogin());
			statement.setString(5, objeto.getSenha());
			
			statement.executeUpdate();
			connection.commit();
		}
		
		return this.consultar(objeto.getLogin(), objeto.getCpf());
	}
	
	public Usuario consultar(String login, String cpf) throws Exception {
		
		Usuario objetoUsuario = new Usuario();
		
		String sql = "SELECT id, nome, cpf, email, login, senha, data_cadastro" + 
				"  FROM public.usuario"
				+ " WHERE UPPER(login) = UPPER(?)"
				+ " OR cpf = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, objetoUsuario.getLogin());
		statement.setString(2, objetoUsuario.getCpf());
		
		ResultSet resultado = statement.executeQuery();
		if (resultado.next()) {/*Se tiver algum resultado*/
			objetoUsuario.setId(resultado.getLong("id"));
			objetoUsuario.setNome(resultado.getString("nome"));
			objetoUsuario.setCpf(resultado.getString("cpf"));
			objetoUsuario.setEmail(resultado.getString("email"));
			objetoUsuario.setLogin(resultado.getString("login"));
			objetoUsuario.setSenha(resultado.getString("senha"));
		}
		
		return objetoUsuario;
	}
	
	public Usuario consultarPorId(String idUser) throws Exception {
		
		Usuario objetoUsuario = new Usuario();
		
		String sql = "SELECT id, nome, cpf, email, login, senha, data_cadastro" + 
				"  FROM public.usuario"
				+ " WHERE id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(idUser));
		
		ResultSet resultado = statement.executeQuery();
		if (resultado.next()) {/*Se tiver algum resultado*/
			objetoUsuario.setId(resultado.getLong("id"));
			objetoUsuario.setNome(resultado.getString("nome"));
			objetoUsuario.setCpf(resultado.getString("cpf"));
			objetoUsuario.setEmail(resultado.getString("email"));
			objetoUsuario.setLogin(resultado.getString("login"));
			objetoUsuario.setSenha(resultado.getString("senha"));
		}
		
		return objetoUsuario;
	}
	
	public List<Usuario> buscarPorNome(String nome) throws Exception {
		
		List<Usuario> listaUsuarios = new ArrayList<>();
		
		String sql = "SELECT id, nome, cpf, email, login, senha, data_cadastro" + 
				"  FROM public.usuario"
				+ " WHERE UPPER(nome) LIKE UPPER(?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nome + "%");
		
		ResultSet resultado = statement.executeQuery();
		while (resultado.next()) {/*Enquanto tiver resultados*/
			Usuario objetoUsuario = new Usuario();
			objetoUsuario.setId(resultado.getLong("id"));
			objetoUsuario.setNome(resultado.getString("nome"));
			objetoUsuario.setCpf(resultado.getString("cpf"));
			objetoUsuario.setEmail(resultado.getString("email"));
			objetoUsuario.setLogin(resultado.getString("login"));
			objetoUsuario.setDataCadastro(resultado.getTimestamp("data_cadastro"));
			//objetoUsuario.setSenha(resultado.getString("senha"));
			
			listaUsuarios.add(objetoUsuario);
		}
		
		return listaUsuarios;
	}
	
	public boolean validarLogin(String login) throws Exception {
		
		String sql = "SELECT COUNT(1) > 0 AS existe "
				+ " FROM public.usuario" + 
				" WHERE UPPER(login) = UPPER(?);";
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
				" WHERE cpf = ?;";
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
				" WHERE id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, idObjetoUsuario);
		
		statement.executeUpdate();
		connection.commit();
	}
}
