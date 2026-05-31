package edu.yan.gestaobiblioteca.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "autor")
public class Autor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, updatable = false)
	private Long id;
	
	@Column(updatable = true, nullable = false, length = 100)
	String prenome;
	
	@Column(updatable = true, nullable = false, length = 100)
	String sobrenome;
	
	@Column(updatable = true, nullable = true, length = 20)
	String sufixo;
	
	//TODO: REMOVER NOME AUTORIZADO (agora ele vai ter o pseudonimo vinculado)
	//@Column(updatable = true, nullable = false, name="nome_autorizado", length = 222)
    //String nomeAutorizado;
    
	//TODO: REMOVER NOME DE EXIBIÇÃO (agora ele vai ser apenas concatenado)
	//@Column(updatable = true, nullable = false, name="nome_exibicao", length = 222)
    //String nomeExibicao;
	
	@Column(updatable = true, nullable = true, name="data_nascimento")
	Date dataNascimento;
	
	@Column(updatable = true, nullable = true, name="data_morte")
	Date dataMorte;
	
	@Column(columnDefinition = "TEXT")
	String biografia;
	
	@Column(updatable = true, nullable = false)
	boolean anonimo = false;
	
    /**
     * Referencia do autor verdadeiro, caso este seja um pseudonimo
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_verdadeiro_id")
    private Autor autorVerdadeiro;

    /**
     * Pseudônimos deste autor
     */
    @OneToMany(mappedBy = "autorVerdadeiro")
    private List<Autor> pseudonimos = new ArrayList<>();

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

	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataMorte() {
		return dataMorte;
	}
	public void setDataMorte(Date dataMorte) {
		this.dataMorte = dataMorte;
	}

	public String getBiografia() {
		return biografia;
	}
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	
}
