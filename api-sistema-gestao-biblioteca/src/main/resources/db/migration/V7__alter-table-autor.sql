ALTER TABLE autor DROP COLUMN nome_autorizado;
	
ALTER TABLE autor DROP COLUMN nome_exibicao;
	
ALTER TABLE autor ADD COLUMN anonimo BOOLEAN NOT NULL DEFAULT FALSE;
	
ALTER TABLE autor ADD COLUMN autor_verdadeiro_id BIGINT;
	
ALTER TABLE autor ADD CONSTRAINT fk_autor_verdadeiro
    	FOREIGN KEY (autor_verdadeiro_id)
    	REFERENCES autor(id)
    	ON DELETE CASCADE;
