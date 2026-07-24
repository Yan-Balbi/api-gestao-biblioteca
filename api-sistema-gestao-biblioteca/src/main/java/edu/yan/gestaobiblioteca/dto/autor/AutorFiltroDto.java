package edu.yan.gestaobiblioteca.dto.autor;

import java.time.LocalDate;

public class AutorFiltroDto {
	private Long id;
	
	private String nome;
		
	private LocalDate dataNascimento;
	
	private LocalDate dataNascimentoInicio;
	
	private LocalDate dataNascimentoFim;
	
	private LocalDate dataMorte;
	
	private LocalDate dataMorteInicio;
	
	private LocalDate dataMorteFim;
	
	private LocalDate nasceuAntes;
	
	private LocalDate nasceuApos;
	
	private Boolean anonimo;
	
	private boolean vivo;
	
	private boolean morto;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String prenome) {
		this.nome = prenome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public LocalDate getDataNascimentoInicio() {
		return dataNascimentoInicio;
	}
	public void setDataNascimentoInicio(LocalDate dataNascimentoInicio) {
		this.dataNascimentoInicio = dataNascimentoInicio;
	}
	
	public LocalDate getDataNascimentoFim() {
		return dataNascimentoFim;
	}
	public void setDataNascimentoFim(LocalDate dataNascimentoFim) {
		this.dataNascimentoFim = dataNascimentoFim;
	}
	
	public LocalDate getDataMorte() {
		return dataMorte;
	}
	public void setDataMorte(LocalDate dataMorte) {
		this.dataMorte = dataMorte;
	}
	
	public LocalDate getDataMorteInicio() {
		return dataMorteInicio;
	}
	public void setDataMorteInicio(LocalDate dataMorteInicio) {
		this.dataMorteInicio = dataMorteInicio;
	}
	
	public LocalDate getDataMorteFim() {
		return dataMorteFim;
	}
	public void setDataMorteFim(LocalDate dataMorteFim) {
		this.dataMorteFim = dataMorteFim;
	}
	
	public boolean isAnonimo() {
		return anonimo;
	}
	public void setAnonimo(boolean anonimo) {
		this.anonimo = anonimo;
	}
	
	public LocalDate getNasceuAntes() {
		return nasceuAntes;
	}
	public void setNasceuAntes(LocalDate nasceuAntes) {
		this.nasceuAntes = nasceuAntes;
	}
	
	public LocalDate getNasceuApos() {
		return nasceuApos;
	}
	public void setNasceuApos(LocalDate nasceuApos) {
		this.nasceuApos = nasceuApos;
	}
	
	public boolean isVivo() {
		return vivo;
	}
	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}
	
	public boolean isMorto() {
		return morto;
	}
	public void setMorto(boolean morto) {
		this.morto = morto;
	}
	
	
}
