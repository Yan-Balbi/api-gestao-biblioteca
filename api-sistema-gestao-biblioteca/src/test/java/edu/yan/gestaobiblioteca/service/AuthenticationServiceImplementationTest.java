package edu.yan.gestaobiblioteca.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.yan.gestaobiblioteca.exception.usuario.CpfInvalidoException;
import edu.yan.gestaobiblioteca.exception.usuario.CpfJaCadastradoException;
import edu.yan.gestaobiblioteca.exception.usuario.EmailJaCadastradoException;
import edu.yan.gestaobiblioteca.model.Usuario;
import edu.yan.gestaobiblioteca.respository.UsuarioRepository;
import edu.yan.gestaobiblioteca.service.implementations.AuthenticationServiceImplementation;

public class AuthenticationServiceImplementationTest {
	@Mock 
	UsuarioRepository usuarioRepository;
	
	@Mock
	PasswordEncoder passwordEncoder;
	
	@InjectMocks
	AuthenticationServiceImplementation authServiceImpl;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		when(passwordEncoder.encode(anyString())).thenReturn("senhaCriptografada");
	}
	
	//	TESTES CRÍTICOS
	//		1 - para Admin
	
	/*
	 * O admin é um caso especial, ele precisa ser inserido no sistema assim que ele é iniciado pela primeira vez
	 * eu acho que deveria existir uma função chamada 'inserirPrimeiroAdmin' e deixar essa função com validação de cpf normal para add 
	 * novos admins
	 */
	@Test
	void deveLancarExcecaoQuandoCpfInvalido() {
	    Usuario usuario = new Usuario();
	    usuario.setNomeUsuario("nome");
	    usuario.setEmail("email@email.com");
	    usuario.setCpf("123"); // inválido
	    usuario.setSenha("123");

	    assertThrows(CpfInvalidoException.class, () -> {
	    	authServiceImpl.signupAdmin(usuario);  	
	    });
	}
	
	@Test
	void deveLancarExcecaoQuandoCpfJaExiste() {
	    Usuario usuario = new Usuario();
	    //inserindo um cpf válido gerado aleatoriamente
	    usuario.setCpf("197.137.440-71");
	
	    when(usuarioRepository.findUsuarioByCpf("19713744071"))
	        .thenReturn(Optional.of(new Usuario()));
	
	    assertThrows(CpfJaCadastradoException.class, () -> {
	    	authServiceImpl.signupAdmin(usuario);
	    });
	}
	
	@Test
	void deveLancarExcecaoQuandoEmailJaExiste() {
	    Usuario usuario = new Usuario();
	    usuario.setEmail("teste@email.com");
	    usuario.setCpf("197.137.440-71");
	    usuario.setSenha("123");
	
	    when(usuarioRepository.findUsuarioByEmail("teste@email.com"))
	        .thenReturn(Optional.of(new Usuario()));
	
	    assertThrows(EmailJaCadastradoException.class, () -> {
	    	authServiceImpl.signupAdmin(usuario);
	    });
	}
	
	//para Bibliotecário
}
