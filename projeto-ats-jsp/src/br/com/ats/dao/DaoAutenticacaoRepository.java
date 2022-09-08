package br.com.ats.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.ats.classes.entities.Usuario;
import br.com.ats.conexao.SingleConnection;

public class DaoAutenticacaoRepository {

	private Connection connection;
	
	public DaoAutenticacaoRepository() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean validarAutenticacao(Usuario usuario) throws Exception {
		
		String sql = "SELECT login, senha FROM usuario "
				+ " WHERE UPPER(login) = UPPER(?) AND UPPER(senha) = UPPER(?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, usuario.getLogin());
		statement.setString(2, usuario.getSenha());
		
		ResultSet resultado = statement.executeQuery();
		if (resultado.next()) {
			return true;
		}
		return false;
	}
}
