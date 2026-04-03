package edu.yan.gestaobiblioteca.dto.regra;

public class RegraUpdateDto {

	private int duracaoSuspensao;
	
	private int tempoDuracaoAgendamento;
	
	private int tempoExpiracaoAgendamento;
	
	private int quantidadeMaximaEmprestimos;
	
	public RegraUpdateDto() {
		
	}
	
	public RegraUpdateDto(int duracaoSuspensao, int tempoDuracaoAgendamento, int tempoExpiracaoAgendamento, int quantidadeMaximaEmprestimos) {
		setDuracaoSuspensao(duracaoSuspensao);
		setTempoDuracaoAgendamento(tempoDuracaoAgendamento);
		setTempoExpiracaoAgendamento(tempoExpiracaoAgendamento);
		setQuantidadeMaximaEmprestimos(quantidadeMaximaEmprestimos);
	}

	public int getDuracaoSuspensao() {
		return duracaoSuspensao;
	}
	public void setDuracaoSuspensao(int duracaoSuspensao) {
		this.duracaoSuspensao = duracaoSuspensao;
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
