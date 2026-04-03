package edu.yan.gestaobiblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Regra {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;
	
	@Column(nullable = false, name = "duracao_suspensao_usuario")
	private int duracaoSuspensaoUsuario;
	
	@Column(nullable = false, name = "tempo_duracao_agendamento")
	private int tempoDuracaoAgendamento;
	
	@Column(nullable = false, name = "tempo_expiracao_agendamento")
	private int tempoExpiracaoAgendamento;
	
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

	public int getTempoDuracaoAgendamento() {
		return tempoDuracaoAgendamento;
	}
	public void setTempoDuracaoAgendamento(int tempoDuracaoAgendamento) {
		this.tempoDuracaoAgendamento = tempoDuracaoAgendamento;
	}
	
	public int getTempoExpiracaoAgendamento() {
		return tempoExpiracaoAgendamento;
	}
	public void setTempoExpiracaoAgendamento(int tempoExpiracaoAgendamento) {
		this.tempoExpiracaoAgendamento = tempoExpiracaoAgendamento;
	}
	
	public int getQuantidadeMaximaEmprestimos() {
		return quantidadeMaximaEmprestimos;
	}
	public void setQuantidadeMaximaEmprestimos(int quantidadeMaximaEmprestimos) {
		this.quantidadeMaximaEmprestimos = quantidadeMaximaEmprestimos;
	}
}
