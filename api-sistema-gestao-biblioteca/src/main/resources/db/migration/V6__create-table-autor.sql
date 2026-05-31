CREATE TABLE autor (
	id BIGSERIAL PRIMARY KEY,
	
	prenome VARCHAR(100) NOT NULL,
	
	sobrenome VARCHAR(100) NOT NULL,
	
	sufixo VARCHAR(20),
	
	nome_autorizado VARCHAR(222) NOT NULL,
	
	nome_exibicao VARCHAR(222) NOT NULL,
	
	data_nascimento TIMESTAMP,
	
	data_morte TIMESTAMP,
	
	biografia TEXT
)