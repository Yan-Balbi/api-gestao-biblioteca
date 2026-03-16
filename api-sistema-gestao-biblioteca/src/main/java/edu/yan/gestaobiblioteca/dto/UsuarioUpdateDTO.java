package edu.yan.gestaobiblioteca.dto;

public class UsuarioUpdateDTO {
	private String cpf;
	private String nomeUsuario;
	private String email;
	private String senha;
	
	public UsuarioUpdateDTO() {
		
	}
	
	public UsuarioUpdateDTO(String cpf, String nomeUsuario, String email, String senha) {
		setCpf(cpf);
		setNomeUsuario(nomeUsuario);
		setEmail(email);
		setSenha(senha);
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
}
