package edu.yan.gestaobiblioteca.exception.usuario;

public class UsuarioDesativadoException extends RuntimeException{
	public UsuarioDesativadoException(String mensagem) {
		super(mensagem);
	}
}
