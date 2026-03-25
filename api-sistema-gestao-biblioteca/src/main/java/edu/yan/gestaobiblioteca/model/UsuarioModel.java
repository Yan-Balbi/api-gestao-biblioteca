package edu.yan.gestaobiblioteca.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UsuarioModel implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;
	
	@Column(unique = false, length = 11, nullable = true)
	private String cpf;
	
	@Column(length = 100, nullable = false, name = "nome_usuario")
	private String nomeUsuario;
	
	@Column(unique = false, length = 100, nullable = false)
	private String email;
	
	@Column(length = 100, nullable = false)
	private String senha;
	
	@Column(length = 50, nullable = true)
	private String papel;
	
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = true)
    private Date updatedAt = null;
    
    
    @Column(name = "deleted_at", nullable = true)
    private Date deletedAt = null;
	
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

	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	
}
