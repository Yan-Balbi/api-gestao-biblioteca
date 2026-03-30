package edu.yan.gestaobiblioteca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.yan.gestaobiblioteca.dto.usuario.UsuarioUpdateDTO;
import edu.yan.gestaobiblioteca.exception.UsuarioNaoEncontrado;
import edu.yan.gestaobiblioteca.model.UsuarioModel;
import edu.yan.gestaobiblioteca.respository.UsuarioRepository;
import edu.yan.gestaobiblioteca.service.implementations.UsuarioServiceImplementation;

// os nomes dos testes devem descrever comportamentos de regras de negócios

// > Um sistema bem testado não prova que funciona - ele prova que falha corretamente

// Um teste sempre segue a estrutura arranje (when), act ( service.metodo() ) e assert (assertEquals(...), verify(...))

public class UsuarioServiceImplementationTest {
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@InjectMocks
	private UsuarioServiceImplementation usuarioServiceImplementation;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	//TESTES CRÍTICOS
	@Test
	void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
	    Long id = 1L;

	    when(usuarioRepository.findAdminById(id))
	        .thenReturn(Optional.empty());

	    assertThrows(UsuarioNaoEncontrado.class, () -> {
	    	usuarioServiceImplementation.atualizarUsuario(id, new UsuarioUpdateDTO());
	    });
	}
	
	@Test
	void deveLancarExcecaoAoDeletarUsuarioInexistente() {
	    Long id = 1L;

	    when(usuarioRepository.findById(id))
	        .thenReturn(Optional.empty());

	    assertThrows(UsuarioNaoEncontrado.class, () -> {
	    	usuarioServiceImplementation.deletarUsuario(id);
	    });
	}
	
	//testes menos críticos
	@Test
	void deveAtualizarUsuarioComSucesso() {
	    Long id = 1L;

	    UsuarioModel usuario = new UsuarioModel();
	    usuario.setId(id);

	    UsuarioUpdateDTO dto = new UsuarioUpdateDTO();
	    dto.setCpf("123");
	    dto.setEmail("teste@email.com");
	    dto.setNomeUsuario("Yan");
	    dto.setSenha("123456");

	    when(usuarioRepository.findAdminById(id))
	        .thenReturn(Optional.of(usuario));

	    UsuarioModel resultado = usuarioServiceImplementation.atualizarUsuario(id, dto);

	    assertEquals("123", resultado.getCpf());
	    assertEquals("teste@email.com", resultado.getEmail());
	    assertEquals("Yan", resultado.getNomeUsuario());
	    assertEquals("123456", resultado.getSenha());
	}
	
	@Test
	void deveBuscarClientePorCpf() {
	    String cpf = "123";

	    UsuarioModel usuario = new UsuarioModel();

	    when(usuarioRepository.findClienteByCpf(cpf))
	        .thenReturn(Optional.of(usuario));

	    Optional<UsuarioModel> resultado = usuarioServiceImplementation.buscarClientePorCpf(cpf);

	    assertTrue(resultado.isPresent());
	    verify(usuarioRepository).findClienteByCpf(cpf);
	}
}
