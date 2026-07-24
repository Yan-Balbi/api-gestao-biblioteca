package edu.yan.gestaobiblioteca.dto.autor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public class AutorVerdadeiroInsertDto {
	@NotBlank(message = "Campo 'prenome' é obrigatório")
	private String prenome;
	@NotBlank(message = "Campo 'sobrenome' é obrigatório")
	private String sobrenome;
	private String sufixo; //não obrigatorio
	private LocalDate dataNascimento;//não obrigatorio
	private LocalDate dataMorte;//não obrigatorio
	private String biografia;//não obrigatorio
	
	@Valid // <- cascateia a validação para cada item da lista
    private List<AutorPseudonimoInsertDto> pseudonimos = new ArrayList<>();
	
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
	
	public List<AutorPseudonimoInsertDto> getPseudonimos() {
		return pseudonimos;
	}
	public void setPseudonimos(List<AutorPseudonimoInsertDto> pseudonimos) {
		this.pseudonimos = pseudonimos;
	}
}
