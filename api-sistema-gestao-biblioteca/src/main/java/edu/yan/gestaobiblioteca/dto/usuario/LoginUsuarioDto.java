package edu.yan.gestaobiblioteca.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public class LoginUsuarioDto {
	@NotBlank(message = "Campo 'email' é obrigatório")
    private String email;
    
	@NotBlank(message = "Campo 'senha' é obrigatório")
    private String senha;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String password) {
		this.senha = password;
	}
}
