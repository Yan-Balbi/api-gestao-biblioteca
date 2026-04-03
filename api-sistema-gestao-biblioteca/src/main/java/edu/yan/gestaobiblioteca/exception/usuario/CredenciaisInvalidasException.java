package edu.yan.gestaobiblioteca.exception.usuario;

public class CredenciaisInvalidasException extends RuntimeException{
	public CredenciaisInvalidasException(String mensagem) {
		super(mensagem);
	}
}
