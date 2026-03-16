package edu.yan.gestaobiblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UsuarioModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;
	
	@Column(unique = true, length = 11, nullable = true)
	private String cpf;
	
	@Column(length = 100, nullable = false, name = "nome_usuario")
	private String nomeUsuario;
	
	@Column(length = 100, nullable = false)
	private String email;
	
	@Column(length = 100, nullable = false)
	private String senha;
	
	@Column(length = 50, nullable = true)
	private String papel;
	
	//many to many (usuario-papel)
	
	//many to many (usuario-emprestimo)

	//many to one (suspensao-usuario)
	
	public UsuarioModel() {
		
	}
	
	public UsuarioModel(String cpf, String nomeUsuario, String email, String senha) {
		setCpf(cpf);
		setNomeUsuario(nomeUsuario);
		setEmail(email);
		setSenha(senha);
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPapel() {
		return papel;
	}
	public void setPapel(String papel) {
		this.papel = papel;
	}
}
