package edu.yan.gestaobiblioteca.dto.regra;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegraUpdateDto {

	@NotNull(message="Campo 'duracaoSuspensaoUsuario' é obrigatório")
	private int duracaoSuspensaoUsuario;
	
	@NotNull(message="Campo 'tempoDuracaoEmprestimo' é obrigatório")
	private int tempoDuracaoEmprestimo;
	
	@NotNull(message="Campo 'tempoExpiracaoReserva' é obrigatório")
	private int tempoExpiracaoReserva;
	
	@NotNull(message="Campo 'quantidadeMaximaEmprestimos' é importante")
	private int quantidadeMaximaEmprestimos;
	
	public RegraUpdateDto() {
		
	}
	
	public RegraUpdateDto(int duracaoSuspensao, int tempoDuracaoAgendamento, int tempoExpiracaoAgendamento, int quantidadeMaximaEmprestimos) {
		setDuracaoSuspensaoUsuario(duracaoSuspensao);
		setTempoDuracaoEmprestimo(tempoDuracaoAgendamento);
		setTempoExpiracaoReserva(tempoExpiracaoAgendamento);
		setQuantidadeMaximaEmprestimos(quantidadeMaximaEmprestimos);
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
	public void setTempoDuracaoEmprestimo(int tempoDuracaoAgendamento) {
		this.tempoDuracaoEmprestimo = tempoDuracaoAgendamento;
	}

	public int getTempoExpiracaoReserva() {
		return tempoExpiracaoReserva;
	}
	public void setTempoExpiracaoReserva(int tempoExpiracaoAgendamento) {
		this.tempoExpiracaoReserva = tempoExpiracaoAgendamento;
	}

	public int getQuantidadeMaximaEmprestimos() {
		return quantidadeMaximaEmprestimos;
	}
	public void setQuantidadeMaximaEmprestimos(int quantidadeMaximaEmprestimos) {
		this.quantidadeMaximaEmprestimos = quantidadeMaximaEmprestimos;
	}
}
