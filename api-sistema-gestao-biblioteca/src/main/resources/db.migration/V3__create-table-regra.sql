CREATE TABLE regra (
	id BIGSERIAL PRIMARY KEY,
	
	duracao_suspensao_usuario INT,
	
	tempo_duracao_agendamento INT,
	
	tempo_expiracao_agendamento INT,
	
	quantidade_maxima_emprestimos INT
);