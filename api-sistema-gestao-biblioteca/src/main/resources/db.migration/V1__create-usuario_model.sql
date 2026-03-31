CREATE TABLE usuario_model (
    id BIGSERIAL PRIMARY KEY,
    
    cpf VARCHAR(11),
    
    nome_usuario VARCHAR(100) NOT NULL,
    
    email VARCHAR(100) NOT NULL,
    
    senha VARCHAR(100) NOT NULL,
    
    papel VARCHAR(50),
    
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    updated_at TIMESTAMP NULL,
    
    deleted_at TIMESTAMP NULL
);