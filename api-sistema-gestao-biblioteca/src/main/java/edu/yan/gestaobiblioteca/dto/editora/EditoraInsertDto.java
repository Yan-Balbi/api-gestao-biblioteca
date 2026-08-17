package edu.yan.gestaobiblioteca.dto.editora;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EditoraInsertDto {
	@NotBlank(message = "Campo 'nome' é obrigatório")
	private String nome;
	private String descricao;
	private LocalDate dataCriacao;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDate dataCricacao) {
		this.dataCriacao = dataCricacao;
	}
}
