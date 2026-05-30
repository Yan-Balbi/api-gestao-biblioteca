package edu.yan.gestaobiblioteca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.yan.gestaobiblioteca.dto.usuario.UsuarioInsertDto;
import edu.yan.gestaobiblioteca.exception.usuario.CpfInvalidoException;
import edu.yan.gestaobiblioteca.exception.usuario.CpfJaCadastradoException;
import edu.yan.gestaobiblioteca.exception.usuario.EmailJaCadastradoException;
import edu.yan.gestaobiblioteca.model.Usuario;
import edu.yan.gestaobiblioteca.respository.UsuarioRepository;
import edu.yan.gestaobiblioteca.service.implementations.AuthenticationServiceImplementation;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class AuthenticationServiceImplementationTest {
	private Validator validator;
	
	@Mock 
	UsuarioRepository usuarioRepository;
	
	@Mock
	PasswordEncoder passwordEncoder;
	
	@InjectMocks
	AuthenticationServiceImplementation authServiceImpl;
	
	@BeforeEach
	void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
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
	
	/*
	 * testes de emissão de falhas ao realizar atualização de usuario
	 */
	@Test
	void deveLancarExcecaoQuandoCpfInvalido() {
		UsuarioInsertDto usuario = new UsuarioInsertDto();
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
		UsuarioInsertDto usuario = new UsuarioInsertDto();
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
		UsuarioInsertDto usuario = new UsuarioInsertDto();
	    usuario.setEmail("teste@email.com");
	    usuario.setCpf("197.137.440-71");
	    usuario.setSenha("123");
	
	    when(usuarioRepository.findUsuarioByEmail("teste@email.com"))
	        .thenReturn(Optional.of(new Usuario()));
	
	    assertThrows(EmailJaCadastradoException.class, () -> {
	    	authServiceImpl.signupAdmin(usuario);
	    });
	}
	
	/*
	 * testes de validação de dados inseridos no DTO de insert de usuario
	*/
	@Test
	void deveLancarErroQuandoTentarInserirComCpfNulo() {
		UsuarioInsertDto dto = new UsuarioInsertDto(
                null,
                "yan",
                "yan@email.com",
                "123456"
        );

        Set<ConstraintViolation<UsuarioInsertDto>> violations =
                validator.validate(dto);

        assertEquals(1, violations.size());
	}
	
	@Test
	void deveLancarErroQuandoTentarInserirComEmailNulo() {
        UsuarioInsertDto dto = new UsuarioInsertDto(
                null,
                "yan",
                null,
                "123456"
        );

        Set<ConstraintViolation<UsuarioInsertDto>> violations =
                validator.validate(dto);

        assertEquals(2, violations.size());
	}
	
	@Test
	void deveLancarErroQuandoTentarInserirComNomeUsuarioNulo() {
		UsuarioInsertDto dto = new UsuarioInsertDto(
                null,
                null,
                null,
                "123456"
        );

        Set<ConstraintViolation<UsuarioInsertDto>> violations =
                validator.validate(dto);

        assertEquals(3, violations.size());		
	}
	
	@Test
	void deveLancarErroQuandoTentarInserirComSenhaNula() {
		UsuarioInsertDto dto = new UsuarioInsertDto(
                null,
                null,
                null,
                null
        );

        Set<ConstraintViolation<UsuarioInsertDto>> violations =
                validator.validate(dto);

        assertEquals(4, violations.size());				
	}
	
	//para Bibliotecário
}
