package edu.yan.gestaobiblioteca.dto.editora;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;

public class EditoraInsertDto {
	@NotBlank(message = "Nome é obrigatório")
	private String nome;
	private String descricao;
	private Date dataCricacao;
	
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
	
	public Date getDataCricacao() {
		return dataCricacao;
	}
	public void setDataCricacao(Date dataCricacao) {
		this.dataCricacao = dataCricacao;
	}
}
