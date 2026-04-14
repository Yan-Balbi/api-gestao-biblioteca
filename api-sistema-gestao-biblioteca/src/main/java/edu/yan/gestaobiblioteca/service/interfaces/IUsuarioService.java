package edu.yan.gestaobiblioteca.service.interfaces;

import java.util.Optional;

import edu.yan.gestaobiblioteca.dto.usuario.UsuarioUpdateDTO;
import edu.yan.gestaobiblioteca.model.Usuario;

public interface IUsuarioService {
	
	/*Administrador*/
	//UsuarioModel inserirAdmin(UsuarioModel usuarioModel);
	
	Usuario atualizar(Long id, UsuarioUpdateDTO usuarioUpdateDTO);
	
	void deletar(Long id);
	
	/*Bibliotecario*/
	//UsuarioModel inserirBibliotecario(UsuarioModel usuarioModel);
	
	//void deletarBibliotecario(Long id);
	
	Optional<Usuario> buscarBibliotecarioPorCpf(String cpf);
	
	Iterable<Usuario> buscarBibliotecarioPorNome(String cpf);
	
	Iterable<Usuario> buscarTodosBibliotecarios();
	
	/*Cliente*/
	//UsuarioModel inserirCliente(UsuarioModel usuarioModel);
	
	//void deletarCliente(Long id);
	
	Optional<Usuario> buscarClientePorCpf(String cpf);
	
	Iterable<Usuario> buscarClientePorNome(String cpf);
	
	Iterable<Usuario> buscarTodosClientes();
	
}
