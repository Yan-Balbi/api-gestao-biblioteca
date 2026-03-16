package edu.yan.gestaobiblioteca.handler;

public class UsuarioNaoEncontrado extends RuntimeException{
	public UsuarioNaoEncontrado() {
		super("O usuario informado não existe.");
	}
}
