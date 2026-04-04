package edu.yan.gestaobiblioteca.dto.regra;

public class RegraUpdateDto {

	private int duracaoSuspensaoUsuario;
	
	private int tempoDuracaoEmprestimo;
	
	private int tempoExpiracaoReserva;
	
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
