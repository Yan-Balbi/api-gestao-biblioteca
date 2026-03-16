package edu.yan.gestaobiblioteca.service.interfaces;

import java.util.Optional;

import edu.yan.gestaobiblioteca.dto.UsuarioUpdateDTO;
import edu.yan.gestaobiblioteca.model.UsuarioModel;

public interface IUsuarioService {
	
	/*Administrador*/
	UsuarioModel inserirAdmin(UsuarioModel usuarioModel);
	
	UsuarioModel atualizarUsuario(Long id, UsuarioUpdateDTO usuarioUpdateDTO);
	
	/*Bibliotecario*/
	UsuarioModel inserirBibliotecario(UsuarioModel usuarioModel);
	
	//void deletarBibliotecario(Long id);
	
	Optional<UsuarioModel> buscarBibliotecarioPorCpf(String cpf);
	
	Iterable<UsuarioModel> buscarBibliotecarioPorNome(String cpf);
	
	Iterable<UsuarioModel> buscarTodosBibliotecarios();
	
	/*Cliente*/
	UsuarioModel inserirCliente(UsuarioModel usuarioModel);
	
	//void deletarCliente(Long id);
	
	Optional<UsuarioModel> buscarClientePorCpf(String cpf);
	
	Iterable<UsuarioModel> buscarClientePorNome(String cpf);
	
	Iterable<UsuarioModel> buscarTodosClientes();
	
}
