package br.com.ats.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

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
		}
		
		return objeto;
	}
}
