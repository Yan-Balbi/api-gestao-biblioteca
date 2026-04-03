CREATE TABLE suspensao (
    id BIGSERIAL PRIMARY KEY,

	usuario_id BIGINT NOT NULL,
    
    data_inicio TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    data_fim TIMESTAMP NULL,
    
    CONSTRAINT fk_suspensao_usuario
    	FOREIGN KEY (usuario_id)
    	REFERENES usuario_model(i)
    	ON DELETE CASCADE
);