package edu.yan.gestaobiblioteca.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Editora {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;
	
	@Column(updatable = true)
	private String nome;
	
	@Column(updatable = true, name="data_criacao", nullable = true)
	private LocalDate dataCriacao;
	
	/*
	 * TODO: futuramente, criar uma tabela estado e colocar a FK dele aqui
	 */
	
	@Column(updatable = true)
	private String descricao;
	
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private LocalDate createdAt;

    @Column()
    private boolean ativo = true;
    
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = true)
    private LocalDate updatedAt = null;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDate dataCricacao) {
		this.dataCriacao = dataCricacao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public LocalDate getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}
}
