package edu.yan.gestaobiblioteca.dto.editora;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EditoraInsertDto {
	@NotBlank(message = "Campo 'nome' é obrigatório")
	private String nome;
	@NotBlank(message = "Campo 'descricao' faltando no payload")
	private String descricao;
	@NotNull(message = "Campo 'dataCriacao' faltando no payload")
	private Date dataCriacao;
	
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
	
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCricacao) {
		this.dataCriacao = dataCricacao;
	}
}
