package edu.yan.gestaobiblioteca.dto.autor;

import java.time.LocalDate;

public class AutorPseudonimoResponseDto {
    private Long id;
    private String prenome;
    private String sobrenome;
    private String sufixo;
    private String nomeCompleto;
    private LocalDate dataNascimento;
    private LocalDate dataMorte;
    private String biografia;
    private AutorResumoResponseDto autorVerdadeiro;
    private boolean anonimo;
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPrenome() {
		return prenome;
	}
	public void setPrenome(String prenome) {
		this.prenome = prenome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public String getSufixo() {
		return sufixo;
	}
	public void setSufixo(String sufixo) {
		this.sufixo = sufixo;
	}
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public LocalDate getDataMorte() {
		return dataMorte;
	}
	public void setDataMorte(LocalDate dataMorte) {
		this.dataMorte = dataMorte;
	}
	
	public String getBiografia() {
		return biografia;
	}
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	
	public AutorResumoResponseDto getAutorVerdadeiro() {
		return autorVerdadeiro;
	}
	public void setAutorVerdadeiro(AutorResumoResponseDto autorVerdadeiro) {
		this.autorVerdadeiro = autorVerdadeiro;
	}
	
	public boolean isAnonimo() {
		return anonimo;
	}
	public void setAnonimo(boolean anonimo) {
		this.anonimo = anonimo;
	}
}
