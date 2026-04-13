package edu.yan.gestaobiblioteca.service;



import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.yan.gestaobiblioteca.dto.editora.EditoraInsertDto;
import edu.yan.gestaobiblioteca.dto.editora.EditoraUpdateDto;
import edu.yan.gestaobiblioteca.exception.Editora.CampoEditoraInvalidoExcepiton;
import edu.yan.gestaobiblioteca.exception.Editora.EditoraInativaNaoPodeSerEditadaException;
import edu.yan.gestaobiblioteca.exception.Editora.EditoraJaAtivaException;
import edu.yan.gestaobiblioteca.exception.Editora.EditoraJaInativaException;
import edu.yan.gestaobiblioteca.exception.Editora.EditoraNaoEncontradaException;
import edu.yan.gestaobiblioteca.model.Editora;
import edu.yan.gestaobiblioteca.respository.EditoraRepository;
import edu.yan.gestaobiblioteca.service.implementations.EditoraServiceImplementation;

public class EditoraServiceImplementationTest {
	
	@Mock
	EditoraRepository editoraRepository;
	
	@InjectMocks
	EditoraServiceImplementation editoraService;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	//============================ INSERT ============================
	@Test
	void deveLancarExcecaoAoFazerInsercaoComCampoNomeVazio() {
		EditoraInsertDto editora = new EditoraInsertDto();
		editora.setNome("");
		editora.setDescricao("texto de descrição da editora.");
		LocalDate dataCriacao = LocalDate.of(2000, 1, 1);
		editora.setDataCriacao(dataCriacao);
		
		assertThrows(CampoEditoraInvalidoExcepiton.class, () -> {
			editoraService.inserir(editora);
		});
		
	}
	
	@Test
	void deveLancarExcecaoAoFazerInsercaoComCampoNomeComMenosDe5Caracteres() {
		EditoraInsertDto editora = new EditoraInsertDto();
		editora.setNome("abc");
		editora.setDescricao("texto de descrição da editora.");
		LocalDate dataCriacao = LocalDate.of(2000, 1, 1);
		editora.setDataCriacao(dataCriacao);
		
		assertThrows(CampoEditoraInvalidoExcepiton.class, () -> {
			editoraService.inserir(editora);
		});
		
	}
	
	@Test
	void deveLancarExcecaoAoFazerInsercaoComCampoNomeComMaisDe100Caracteres() {
		EditoraInsertDto editora = new EditoraInsertDto();
		editora.setNome("ABCDEFGHIJKLMNOPQRSTUVWXYZ ABCDEFGHIJKLMNOPQRSTUVWXYZ ABCDEFGHIJKLMNOPQRSTUVWXYZ ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		editora.setDescricao("texto de descrição da editora.");
		LocalDate dataCriacao = LocalDate.of(2000, 1, 1);
		editora.setDataCriacao(dataCriacao);
		
		assertThrows(CampoEditoraInvalidoExcepiton.class, () -> {
			editoraService.inserir(editora);
		});
		
	}
	
	//============================ UPDATE ============================
	@Test
	void deveLancarExcecaoAoFazerAtualizacaoDeEditoraComIdInvalido() {
		EditoraUpdateDto editora = new EditoraUpdateDto();
		Long id = (long) 1;
		editora.setNome("Editora ABC");
		editora.setDescricao("texto de descrição da editora.");
		LocalDate dataCriacao = LocalDate.of(2000, 1, 1);
		editora.setDataCriacao(dataCriacao);
		
		when(editoraRepository.findById(id)).thenReturn(Optional.empty());
		
		assertThrows(EditoraNaoEncontradaException.class, ()->{
			editoraService.atualizar(id, editora);
		});
	}
	
	@Test
	void deveLancarExcecaoAoFazerAtualizacaoDeEditoraComCampoNomeComMenosDe5Caracteres() {
		EditoraUpdateDto editora = new EditoraUpdateDto();
		Long id = (long) 1;
		editora.setNome("abc");
		editora.setDescricao("texto de descrição da editora.");
		LocalDate dataCriacao = LocalDate.of(2000, 1, 1);
		editora.setDataCriacao(dataCriacao);
		
		assertThrows(CampoEditoraInvalidoExcepiton.class, ()->{
			editoraService.atualizar(id, editora);
		});
	}
	
	@Test
	void deveLancarExcecaoAoFazerAtualizacaoDeEditoraComCampoNomeComMaisDe100Caracteres() {
		EditoraUpdateDto editora = new EditoraUpdateDto();
		Long id = (long) 1;
		editora.setNome("ABCDEFGHIJKLMNOPQRSTUVWXYZ ABCDEFGHIJKLMNOPQRSTUVWXYZ ABCDEFGHIJKLMNOPQRSTUVWXYZ ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		editora.setDescricao("texto de descrição da editora.");
		LocalDate dataCriacao = LocalDate.of(2000, 1, 1);
		editora.setDataCriacao(dataCriacao);
		
		assertThrows(CampoEditoraInvalidoExcepiton.class, ()->{
			editoraService.atualizar(id, editora);
		});
	}
	
	@Test
	void deveLancarExcecaoAoFazerAtualizacaoDeEditoraComCampoNomeVazio() {
		EditoraUpdateDto editora = new EditoraUpdateDto();
		Long id = (long) 1;
		editora.setNome("");
		editora.setDescricao("texto de descrição da editora.");
		LocalDate dataCriacao = LocalDate.of(2000, 1, 1);
		editora.setDataCriacao(dataCriacao);
		
		assertThrows(CampoEditoraInvalidoExcepiton.class, ()->{
			editoraService.atualizar(id, editora);
		});
	}
	
	@Test
	void deveLancarExcecaoAoFazerAtualizacaoDeEditoraComEditoraInativa() {
		EditoraUpdateDto editoraUpdateDto = new EditoraUpdateDto();
		Long id = (long) 1;
		editoraUpdateDto.setNome("Editora ABC");
		editoraUpdateDto.setDescricao("texto de descrição da editora.");
		LocalDate dataCriacao = LocalDate.of(2000, 1, 1);
		editoraUpdateDto.setDataCriacao(dataCriacao);
		
		Editora editora = new Editora();
		editora.setId(id);
		editora.setNome("Editora ABC");
		editora.setDescricao("texto de descricao da editora.");
		editora.setDataCriacao(dataCriacao);
		
		when(editoraRepository.findById(id)).thenReturn(Optional.of(editora));
		when(editoraRepository.estaAtiva(id)).thenReturn(false);
		
		assertThrows(EditoraInativaNaoPodeSerEditadaException.class, ()->{
			editoraService.atualizar(id, editoraUpdateDto);
		});	
	}
	
	//============================ INATIVAR ============================
	@Test
	void deveLancarExcecaoAoTentarInativarUsuarioInesistente() {
		Editora editora = new Editora();
		Long id = (long) 1;
		LocalDate dataCriacao = LocalDate.of(2000, 1, 1);
		editora.setId(id);
		editora.setNome("Editora ABC");
		editora.setDescricao("Texto de descrição da editora.");
		editora.setDataCriacao(dataCriacao);
		
		when(editoraRepository.findById(id)).thenReturn(Optional.empty());
		
		assertThrows(EditoraNaoEncontradaException.class, () -> {
			editoraService.inativar(id);
		});
	}
	
	@Test
	void deveLancarExcecaoAoTentarInativarUsuarioJaInativo() {
		Editora editora = new Editora();
		Long id = (long) 1;
		LocalDate dataCriacao = LocalDate.of(2000, 1, 1);
		editora.setId(id);
		editora.setNome("Editora ABC");
		editora.setDescricao("Texto de descrição da editora.");
		editora.setDataCriacao(dataCriacao);
		
		when(editoraRepository.findById(id)).thenReturn(Optional.of(editora));
		when(editoraRepository.estaAtiva(id)).thenReturn(false);
		
		assertThrows(EditoraJaInativaException.class, () -> {
			editoraService.inativar(id);
		});
	}

	//============================ REATIVAR ============================
	@Test
	void deveLancarExcecaoAoTentarAtivarUsuarioInesistente() {
		Editora editora = new Editora();
		Long id = (long) 1;
		LocalDate dataCriacao = LocalDate.of(2000, 1, 1);
		editora.setId(id);
		editora.setNome("Editora ABC");
		editora.setDescricao("Texto de descrição da editora.");
		editora.setDataCriacao(dataCriacao);
		
		when(editoraRepository.findById(id)).thenReturn(Optional.empty());
		
		assertThrows(EditoraNaoEncontradaException.class, () -> {
			editoraService.reativar(id);
		});
	}
	
	@Test
	void deveLancarExcecaoAoTentarAtivarUsuarioJaInativo() {
		Editora editora = new Editora();
		Long id = (long) 1;
		LocalDate dataCriacao = LocalDate.of(2000, 1, 1);
		editora.setId(id);
		editora.setNome("Editora ABC");
		editora.setDescricao("Texto de descrição da editora.");
		editora.setDataCriacao(dataCriacao);
		
		when(editoraRepository.findById(id)).thenReturn(Optional.of(editora));
		when(editoraRepository.estaAtiva(id)).thenReturn(true);
		
		assertThrows(EditoraJaAtivaException.class, () -> {
			editoraService.reativar(id);
		});
	}
}
