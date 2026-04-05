package edu.yan.gestaobiblioteca.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.yan.gestaobiblioteca.dto.regra.RegraUpdateDto;
import edu.yan.gestaobiblioteca.exception.regra.QuantidadeMaximaEmprestimosInvalidaException;
import edu.yan.gestaobiblioteca.exception.regra.RegraJaInseridaException;
import edu.yan.gestaobiblioteca.exception.regra.RegraNaoEncontradaException;
import edu.yan.gestaobiblioteca.exception.regra.TempoDeExpiracaoReservaInvalidaException;
import edu.yan.gestaobiblioteca.exception.regra.TempoDuracaoEmprestimoInvalidaException;
import edu.yan.gestaobiblioteca.model.Regra;
import edu.yan.gestaobiblioteca.respository.RegraRepository;
import edu.yan.gestaobiblioteca.service.implementations.RegraServiceImplementation;

public class RegraServiceImplementationTest {
	@Mock
	RegraRepository regraRepository;
	
	@InjectMocks
	RegraServiceImplementation regraServiceImplementation;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void deveLancarExcecaoSeNaoEncontrarRegraInserida() {
		RegraUpdateDto regra = new RegraUpdateDto();
		regra.setDuracaoSuspensaoUsuario(14);
		regra.setQuantidadeMaximaEmprestimos(5);
		regra.setTempoDuracaoEmprestimo(14);
		regra.setTempoExpiracaoReserva(3);
		
		when(regraRepository.findById((long) 1))
        .thenReturn(Optional.empty());
		
		assertThrows(RegraNaoEncontradaException.class, () -> {
			regraServiceImplementation.atualizar((long) 1, regra);
		});
	}
	
	@Test
	void deveLancarExcecaoSeJaTiverRegraInserida() {
		Regra regra = new Regra();
		regra.setId((long) 1);
		regra.setDuracaoSuspensaoUsuario(14);
		regra.setQuantidadeMaximaEmprestimos(5);
		regra.setTempoDuracaoEmprestimo(14);
		regra.setTempoExpiracaoReserva(3);
		
		when(regraRepository.haRegraInserida())
        .thenReturn(true);
		
		assertThrows(RegraJaInseridaException.class, () -> {
			regraServiceImplementation.inserir(regra);
		});
	}
	
	@Test
	void deveLancarExcecaoSeTiverTempoDeDuracaoEmprestimoInvalido() {
		Regra regra = new Regra();
		regra.setId((long) 1);
		regra.setDuracaoSuspensaoUsuario(14);
		regra.setQuantidadeMaximaEmprestimos(5);
		regra.setTempoDuracaoEmprestimo(-1);
		regra.setTempoExpiracaoReserva(3);
		
		when(regraRepository.haRegraInserida())
        .thenReturn(false);
		
		assertThrows(TempoDuracaoEmprestimoInvalidaException.class, () -> {
			regraServiceImplementation.inserir(regra);
		});
	}
	
	@Test
	void deveLancarExcecaoSeTiverQuantidadeMaximaEmprestimosInvalido() {
		Regra regra = new Regra();
		regra.setDuracaoSuspensaoUsuario(14);
		regra.setQuantidadeMaximaEmprestimos(-1);
		regra.setTempoDuracaoEmprestimo(14);
		regra.setTempoExpiracaoReserva(3);
		
		when(regraRepository.haRegraInserida())
        .thenReturn(false);
		
		assertThrows(QuantidadeMaximaEmprestimosInvalidaException.class, () -> {
			regraServiceImplementation.inserir(regra);
		});
	}
	
	@Test
	void deveLancarExcecaoSeTiverTempoDuracaoEmprestimoInvalido() {
		Regra regra = new Regra();
		regra.setDuracaoSuspensaoUsuario(14);
		regra.setQuantidadeMaximaEmprestimos(5);
		regra.setTempoDuracaoEmprestimo(-1);
		regra.setTempoExpiracaoReserva(3);
		
		when(regraRepository.haRegraInserida())
        .thenReturn(false);
		
		assertThrows(TempoDuracaoEmprestimoInvalidaException.class, () -> {
			regraServiceImplementation.inserir(regra);
		});
	}
	
	@Test
	void deveLancarExcecaoSeTiverTempoExpiracaoReservaInvalida() {
		Regra regra = new Regra();
		regra.setDuracaoSuspensaoUsuario(14);
		regra.setQuantidadeMaximaEmprestimos(5);
		regra.setTempoDuracaoEmprestimo(14);
		regra.setTempoExpiracaoReserva(-1);
		
		when(regraRepository.haRegraInserida())
        .thenReturn(false);
		
		assertThrows(TempoDeExpiracaoReservaInvalidaException.class, () -> {
			regraServiceImplementation.inserir(regra);
		});
	}
	
}
