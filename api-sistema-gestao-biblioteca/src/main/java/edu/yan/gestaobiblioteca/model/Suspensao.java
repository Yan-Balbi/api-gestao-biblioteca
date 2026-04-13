package edu.yan.gestaobiblioteca.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Suspensao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;
	
	//FetchType.LAZY precisa carregar os dados do banco
	//FetchType.EAGER o objeto já vem com oss objetos associados carregados
    @ManyToOne(fetch = FetchType.LAZY) // ou EAGER, dependendo do uso
    @JoinColumn(name = "usuario_id", nullable = false) // define a coluna como FK
    private Usuario usuario;
	
	@CreationTimestamp
    @Column(updatable = false, name = "data_inicio")
	private LocalDate dataInicio;
	
	@Column(nullable = true,name = "data_fim")
	private LocalDate dataFim;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	
	
}
