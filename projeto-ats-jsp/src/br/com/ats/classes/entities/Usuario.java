package br.com.ats.classes.entities;

import java.sql.Timestamp;

public class Usuario extends GenericDomain {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String cpf;
	private String email;
	private String login;
	private String senha;
	private Timestamp dataCadastro;
	
	private boolean useradmin;
	
	public Usuario() {
		
	}
	
	public boolean isNovoUser() {
		if (id == null) {
			return true;/*Inseri um novo id*/
		} else if(id != null && id > 0) {
			return false;/*Atualiza um id existente*/
		}
		return id == null;
	}
	
	public boolean isUseradmin() {
		return useradmin;
	}
	
	public void setUseradmin(boolean useradmin) {
		this.useradmin = useradmin;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Timestamp getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	

}
