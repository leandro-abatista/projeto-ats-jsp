package br.com.ats.conexao;

import java.sql.Connection;
import java.sql.DriverManager;


public class SingleConnection {

	private static String urlBanco = "jdbc:postgresql://127.0.0.1:5433/banco_ats_jsp?autoReconnect=true";
	private static String user = "postgres";
	private static String password = "1234";
	private static Connection connection = null;
	
	
	static {
		conectar();
	}
	
	public SingleConnection() {
		conectar();
	}
	
	private static void conectar() {
		try {
			
			if (connection == null) {
				Class.forName("org.postgresql.Driver");/*Carrega o driver de conexão  do banco*/
				connection = DriverManager.getConnection(urlBanco, user, password);
				connection.setAutoCommit(false);/*false para não efetuar commits de forma automática*/
				System.out.println("banco de dados conectado.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
}
