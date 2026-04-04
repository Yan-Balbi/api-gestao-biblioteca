package edu.yan.gestaobiblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Regra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;
	
	@Column(nullable = false, name = "duracao_suspensao_usuario")
	private int duracaoSuspensaoUsuario;
	
	@Column(nullable = false, name = "tempo_duracao_emprestimo")
	private int tempoDuracaoEmprestimo;
	
	@Column(nullable = false, name = "tempo_expiracao_reserva")
	private int tempoExpiracaoReserva;
	
	@Column(nullable = false, name = "quantidade_maxima_emprestimos")
	private int quantidadeMaximaEmprestimos;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getDuracaoSuspensaoUsuario() {
		return duracaoSuspensaoUsuario;
	}
	public void setDuracaoSuspensaoUsuario(int duracaoSuspensao) {
		this.duracaoSuspensaoUsuario = duracaoSuspensao;
	}

	public int getTempoDuracaoEmprestimo() {
		return tempoDuracaoEmprestimo;
	}
	public void setTempoDuracaoEmprestimo(int tempoDuracaoEmprestimo) {
		this.tempoDuracaoEmprestimo = tempoDuracaoEmprestimo;
	}
	
	public int getTempoExpiracaoReserva() {
		return tempoExpiracaoReserva;
	}
	public void setTempoExpiracaoReserva(int tempoExpiracaoReserva) {
		this.tempoExpiracaoReserva = tempoExpiracaoReserva;
	}
	
	public int getQuantidadeMaximaEmprestimos() {
		return quantidadeMaximaEmprestimos;
	}
	public void setQuantidadeMaximaEmprestimos(int quantidadeMaximaEmprestimos) {
		this.quantidadeMaximaEmprestimos = quantidadeMaximaEmprestimos;
	}
}
