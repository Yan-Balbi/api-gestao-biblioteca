package edu.yan.gestaobiblioteca.service.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.yan.gestaobiblioteca.dto.UsuarioUpdateDTO;
import edu.yan.gestaobiblioteca.handler.UsuarioNaoEncontrado;
import edu.yan.gestaobiblioteca.model.UsuarioModel;
import edu.yan.gestaobiblioteca.respository.UsuarioRepository;
import edu.yan.gestaobiblioteca.service.interfaces.IUsuarioService;
import jakarta.transaction.Transactional;

@Service
public class UsuarioImplementationService implements IUsuarioService{

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public UsuarioModel inserirUsuario(UsuarioModel usuarioModel) {
		if(!usuarioRepository.findAdminByCpf(usuarioModel.getCpf()).isEmpty()) {
			throw new IllegalArgumentException("O cpf informado já está cadastrado");
		}
		UsuarioModel usuarioInserido = usuarioRepository.save(usuarioModel);
		return usuarioInserido;
	}
	
	@Override
	public UsuarioModel inserirAdmin(UsuarioModel usuarioModel) {
		usuarioModel.setPapel("ROLE_ADMIN");
		return inserirUsuario(usuarioModel);
	}

	@Override
	@Transactional
	public UsuarioModel atualizarUsuario(Long id, UsuarioUpdateDTO usuarioUpdateDTO) {
		UsuarioModel usuarioBd = usuarioRepository.findAdminById(id).orElseThrow(()-> new UsuarioNaoEncontrado());
		usuarioBd.setCpf(usuarioUpdateDTO.getCpf());
		usuarioBd.setEmail(usuarioUpdateDTO.getEmail());
		usuarioBd.setNomeUsuario(usuarioUpdateDTO.getNomeUsuario());
		usuarioBd.setSenha(usuarioUpdateDTO.getSenha());
		return usuarioBd;
	}

	@Override
	public UsuarioModel inserirBibliotecario(UsuarioModel usuarioModel) {
		usuarioModel.setPapel("ROLE_BIBLIOTECARIO");
		return inserirUsuario(usuarioModel);
	}

	@Override
	public Optional<UsuarioModel> buscarBibliotecarioPorCpf(String cpf) {
		return usuarioRepository.findBibliotecarioByCpf(cpf);
	}

	@Override
	public Iterable<UsuarioModel> buscarBibliotecarioPorNome(String nome) {

		return usuarioRepository.findBibliotecarioByNome(nome);
	}

	@Override
	public Iterable<UsuarioModel> buscarTodosBibliotecarios() {
		
		return usuarioRepository.findTodosBibliotecarios();
	}

	@Override
	public UsuarioModel inserirCliente(UsuarioModel usuarioModel) {
		usuarioModel.setPapel("ROLE_CLIENTE");
		return inserirUsuario(usuarioModel);
	}

	@Override
	public Optional<UsuarioModel> buscarClientePorCpf(String cpf) {
		return usuarioRepository.findClienteByCpf(cpf);
	}

	@Override
	public Iterable<UsuarioModel> buscarClientePorNome(String nome) {

		return usuarioRepository.findClienteByNome(nome);
	}

	@Override
	public Iterable<UsuarioModel> buscarTodosClientes() {

		return usuarioRepository.findTodosClientes();
	}

}
