package edu.yan.gestaobiblioteca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.yan.gestaobiblioteca.dto.autor.AutorPseudonimoInsertDto;
import edu.yan.gestaobiblioteca.dto.autor.AutorPseudonimoUpdateDto;
import edu.yan.gestaobiblioteca.dto.autor.AutorVerdadeiroInsertDto;
import edu.yan.gestaobiblioteca.dto.autor.AutorVerdadeiroUpdateDto;
import edu.yan.gestaobiblioteca.exception.autor.AutorJaEhPseudonimoException;
import edu.yan.gestaobiblioteca.exception.autor.AutorNaoEhPseudonimoException;
import edu.yan.gestaobiblioteca.exception.autor.AutorNaoEhVerdadeiroException;
import edu.yan.gestaobiblioteca.exception.autor.AutorNaoEncontradoException;
import edu.yan.gestaobiblioteca.model.Autor;
import edu.yan.gestaobiblioteca.respository.AutorRepository;
import edu.yan.gestaobiblioteca.service.implementations.AutorServiceImplementation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class AutorServiceImplementationTest {
	private Validator validator;
	
	@Mock
	AutorRepository autorRepository;
	
	@InjectMocks
	AutorServiceImplementation autorServiceImpl;
	
	@BeforeEach
	void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		MockitoAnnotations.openMocks(this);
	}
	
	//	TESTES CRÍTICOS
	//		1 - inserção de Autor Verdadeiro
	
	@Test
	void deveLancarExcecaoQuandoNaoEncontrarAutor() {
		AutorVerdadeiroInsertDto autorDto = new AutorVerdadeiroInsertDto();
		autorDto.setPrenome("Willian");
		autorDto.setSobrenome("Gibson");
		autorDto.setSufixo(null);
		LocalDate nascimento = LocalDate.of(1948, Month.MARCH, 17);
		autorDto.setDataNascimento(nascimento);
		
	    Autor autorSalvo = new Autor();
	    autorSalvo.setId(1L);

		when(autorRepository.save(any(Autor.class)))
		.thenReturn(autorSalvo);
		
	    when(autorRepository.findByIdComPseudonimos(1L))
        .thenReturn(Optional.empty()); //simulando que não encontrou o id

		
		assertThrows(AutorNaoEncontradoException.class, () -> {
			autorServiceImpl.inserirAutorVerdadeiro(autorDto);
		});
		
	    verify(autorRepository).save(any());
	    verify(autorRepository).findByIdComPseudonimos(1L);
	}
	
	//		2 - inserção de Autor PSEUDONIMO
	
	@Test
	void deveLancarAutorNaoEncontradoQuandoIdNaoExiste() {

	    Long autorId = 1L;

	    AutorPseudonimoInsertDto dto = new AutorPseudonimoInsertDto();
	    dto.setPrenome("William");
	    dto.setSobrenome("Gibson");
	    
	    when(autorRepository.findById(autorId))
	            .thenReturn(Optional.empty());

	    assertThrows(
	            AutorNaoEncontradoException.class,
	            () -> autorServiceImpl.inserirAutorPseudonimo(autorId, dto));

	    verify(autorRepository).findById(autorId);
	    verify(autorRepository, never()).save(any());
	}
	
	@Test
	void deveLancarExcecaoQuandoAutorInformadoJaEhPseudonimo() {
	    Long autorId = 1L;

	    Autor autorRaiz = new Autor();
	    autorRaiz.setId(10L);

	    Autor pseudonimo = new Autor();
	    pseudonimo.setId(autorId);
	    pseudonimo.setAutorVerdadeiro(autorRaiz);

	    AutorPseudonimoInsertDto dto = new AutorPseudonimoInsertDto();
	    dto.setPrenome("William");
	    dto.setSobrenome("Gibson");

	    when(autorRepository.findById(autorId))
	            .thenReturn(Optional.of(pseudonimo));

	    assertThrows(
	            AutorJaEhPseudonimoException.class,
	            () -> autorServiceImpl.inserirAutorPseudonimo(autorId, dto));

	    verify(autorRepository).findById(autorId);
	    verify(autorRepository, never()).save(any());
	}
	
	//		3 - atualização de Autor Verdadeiro
	@Test
	void deveAtualizarAutorVerdadeiro() {

	    // Arrange
	    Long id = 1L;

	    Autor autor = new Autor();
	    autor.setId(id);
	    autor.setPrenome("Wiian");
	    autor.setSobrenome("Gibson");
	    autor.setAutorVerdadeiro(null);

	    AutorVerdadeiroUpdateDto dto = new AutorVerdadeiroUpdateDto();
	    dto.setPrenome("William");
	    dto.setSobrenome("Gibson");
	    dto.setPseudonimos(Collections.emptyList());

	    when(autorRepository.findByIdComPseudonimos(id))
	            .thenReturn(Optional.of(autor));

	    when(autorRepository.save(any(Autor.class)))
	            .thenAnswer(invocation -> invocation.getArgument(0));

	    // Act
	    Autor resultado = autorServiceImpl.atualizarAutorVerdadeiro(id, dto);

	    // Assert
	    assertNotNull(resultado);
	    assertEquals("William", resultado.getPrenome());
	    assertEquals("Gibson", resultado.getSobrenome());
	    assertNull(resultado.getSufixo());

	    verify(autorRepository).findByIdComPseudonimos(id);
	    verify(autorRepository).save(autor);
	}
	
	@Test
	void deveLancarAutorNaoEncontradoQuandoAutorNaoExiste() {

	    Long id = 1L;

	    when(autorRepository.findByIdComPseudonimos(id))
	            .thenReturn(Optional.empty());

	    AutorVerdadeiroUpdateDto dto = new AutorVerdadeiroUpdateDto();

	    assertThrows(
	            AutorNaoEncontradoException.class,
	            () -> autorServiceImpl.atualizarAutorVerdadeiro(id, dto));

	    verify(autorRepository).findByIdComPseudonimos(id);
	    verify(autorRepository, never()).save(any());
	}
	
	@Test
	void deveLancarExcecaoQuandoAutorForPseudonimo() {

	    Long id = 1L;

	    Autor autorRaiz = new Autor();
	    autorRaiz.setId(10L);

	    Autor pseudonimo = new Autor();
	    pseudonimo.setId(id);
	    pseudonimo.setAutorVerdadeiro(autorRaiz);

	    when(autorRepository.findByIdComPseudonimos(id))
	            .thenReturn(Optional.of(pseudonimo));

	    AutorVerdadeiroUpdateDto dto = new AutorVerdadeiroUpdateDto();

	    assertThrows(
	            AutorNaoEhVerdadeiroException.class,
	            () -> autorServiceImpl.atualizarAutorVerdadeiro(id, dto));

	    verify(autorRepository).findByIdComPseudonimos(id);
	    verify(autorRepository, never()).save(any());
	}
	
	@Test
	void deveSincronizarPseudonimosAoAtualizarAutor() {

	    Long autorId = 1L;

	    Autor autor = new Autor();
	    autor.setId(autorId);
	    autor.setAutorVerdadeiro(null);

	    // pseudônimo que será atualizado
	    Autor pseudo1 = new Autor();
	    pseudo1.setId(10L);
	    pseudo1.setPrenome("Alb3rt0");
	    pseudo1.setSobrenome("Ca3iro");
	    pseudo1.setAutorVerdadeiro(autor);

	    // pseudônimo que será removido
	    Autor pseudo2 = new Autor();
	    pseudo2.setId(20L);
	    pseudo2.setPrenome("Ricardo");
	    pseudo2.setSobrenome("Reis");
	    pseudo2.setAutorVerdadeiro(autor);

	    autor.setPseudonimos(new ArrayList<>(List.of(pseudo1, pseudo2)));

	    // DTO de atualização do pseudônimo existente
	    AutorPseudonimoUpdateDto dtoExistente = new AutorPseudonimoUpdateDto();
	    dtoExistente.setId(10L);
	    dtoExistente.setPrenome("Alberto");
	    dtoExistente.setSobrenome("Caeiro");

	    // DTO de novo pseudônimo
	    AutorPseudonimoUpdateDto dtoNovo = new AutorPseudonimoUpdateDto();
	    dtoNovo.setPrenome("Álvaro");
	    dtoNovo.setSobrenome("de Campos");

	    AutorVerdadeiroUpdateDto dto = new AutorVerdadeiroUpdateDto();
	    dto.setPrenome("Fernando");
	    dto.setSobrenome("Pessoa");
	    dto.setPseudonimos(List.of(dtoExistente, dtoNovo));

	    when(autorRepository.findByIdComPseudonimos(autorId))
	            .thenReturn(Optional.of(autor));

	    when(autorRepository.save(any()))
	            .thenAnswer(invocation -> invocation.getArgument(0));

	    // Act
	    Autor resultado = autorServiceImpl.atualizarAutorVerdadeiro(autorId, dto);

	    // Assert

	    // Continua com apenas 2 pseudônimos
	    assertEquals(2, resultado.getPseudonimos().size());

	    // O pseudônimo antigo foi atualizado
	    Autor atualizado = resultado.getPseudonimos().stream()
	            .filter(p -> Long.valueOf(10L).equals(p.getId()))
	            .findFirst()
	            .orElseThrow();

	    assertEquals("Alberto", atualizado.getPrenome());
	    assertEquals("Caeiro", atualizado.getSobrenome());
	    
	    // Existe um pseudônimo novo (sem id)
	    assertTrue(resultado.getPseudonimos().stream()
	            .anyMatch(p -> p.getId() == null &&
	                    "Álvaro".equals(p.getPrenome()) &&
	                    "de Campos".equals(p.getSobrenome())
	                    ));

	    // O pseudônimo removido não existe mais
	    assertFalse(resultado.getPseudonimos().stream()
	            .anyMatch(p -> Long.valueOf(20L).equals(p.getId())));

	    verify(autorRepository).save(autor);
	}
	
	//		4 - atualização de Autor Verdadeiro
	@Test
	void deveAtualizarAutorPseudonimo() {

	    Long id = 1L;

	    Autor pseudonimo = new Autor();
	    pseudonimo.setId(id);
	    pseudonimo.setAnonimo(true);
	    pseudonimo.setPrenome("Alb3rt0");
	    pseudonimo.setSobrenome("Ca3iro");

	    AutorPseudonimoUpdateDto dto = new AutorPseudonimoUpdateDto();
	    dto.setPrenome("Alberto");
	    dto.setSobrenome("Caeiro");

	    when(autorRepository.findByIdComAutorVerdadeiro(id))
	            .thenReturn(Optional.of(pseudonimo));

	    when(autorRepository.save(any()))
	            .thenAnswer(invocation -> invocation.getArgument(0));

	    // Act
	    Autor resultado = autorServiceImpl.atualizarAutorPseudonimo(id, dto);

	    // Assert
	    assertEquals("Alberto", resultado.getPrenome());
	    assertEquals("Caeiro", resultado.getSobrenome());

	    verify(autorRepository).findByIdComAutorVerdadeiro(id);
	    verify(autorRepository).save(pseudonimo);
	}
	
	@Test
	void deveLancarAutorNaoEncontradoAoAtualizarPseudonimo() {

	    Long id = 1L;

	    when(autorRepository.findById(id))
	            .thenReturn(Optional.empty());

	    AutorPseudonimoUpdateDto dto = new AutorPseudonimoUpdateDto();

	    assertThrows(
	            AutorNaoEncontradoException.class,
	            () -> autorServiceImpl.atualizarAutorPseudonimo(id, dto));

	    verify(autorRepository).findByIdComAutorVerdadeiro(id);
	    verify(autorRepository, never()).save(any());
	}
	
	@Test
	void deveLancarExcecaoQuandoAutorNaoForPseudonimo() {

	    Long id = 1L;

	    Autor autor = new Autor();
	    autor.setId(id);
	    autor.setAnonimo(false);

	    when(autorRepository.findByIdComAutorVerdadeiro(id))
	            .thenReturn(Optional.of(autor));

	    AutorPseudonimoUpdateDto dto = new AutorPseudonimoUpdateDto();

	    assertThrows(
	            AutorNaoEhPseudonimoException.class,
	            () -> autorServiceImpl.atualizarAutorPseudonimo(id, dto));

	    verify(autorRepository).findByIdComAutorVerdadeiro(id);
	    verify(autorRepository, never()).save(any());
	}
	
	
}
