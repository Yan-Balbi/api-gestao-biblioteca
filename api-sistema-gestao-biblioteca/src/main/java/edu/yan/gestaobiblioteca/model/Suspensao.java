package edu.yan.gestaobiblioteca.model;

import java.util.Date;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;
	
	//FetchType.LAZY precisa carregar os dados do banco
	//FetchType.EAGER o objeto já vem com oss objetos associados carregados
    @ManyToOne(fetch = FetchType.LAZY) // ou EAGER, dependendo do uso
    @JoinColumn(name = "usuario_id", nullable = false) // define a coluna como FK
    private Usuario usuario;
	
	@CreationTimestamp
    @Column(updatable = false, name = "data_inicio")
	private Date dataInicio;
	
	@Column(nullable = true,name = "data_fim")
	private Date dataFim;
}
