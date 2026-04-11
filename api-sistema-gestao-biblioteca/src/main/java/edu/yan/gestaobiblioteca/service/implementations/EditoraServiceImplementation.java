package edu.yan.gestaobiblioteca.service.implementations;

import edu.yan.gestaobiblioteca.dto.editora.EditoraInsertDto;
import edu.yan.gestaobiblioteca.dto.editora.EditoraUpdateDto;
import edu.yan.gestaobiblioteca.exception.Editora.EditoraJaAtivaException;
import edu.yan.gestaobiblioteca.exception.Editora.EditoraJaInativaException;
import edu.yan.gestaobiblioteca.exception.Editora.EditoraNaoEncontradaException;
import edu.yan.gestaobiblioteca.exception.Editora.InsiEmUsoException;
import edu.yan.gestaobiblioteca.model.Editora;
import edu.yan.gestaobiblioteca.respository.EditoraRepository;
import edu.yan.gestaobiblioteca.service.interfaces.IEditoraService;
import jakarta.transaction.Transactional;

public class EditoraServiceImplementation implements IEditoraService{

	EditoraRepository editoraRepository;
	
	public EditoraServiceImplementation(EditoraRepository editoraRepository) {
		this.editoraRepository = editoraRepository;
	}
	
	@Override
	public Editora inserir(EditoraInsertDto editoraInsertDto) {
		//podem existir editoras com o mesmo nome		  
		if( !editoraRepository.findEditoraByInsi(editoraInsertDto.getInsi()).isEmpty() ) {
			throw new InsiEmUsoException("O insi inserido '"+editoraInsertDto.getInsi()+"' já está em uso");
		}
		Editora editoraBd = new Editora();
		editoraBd.setInsi(editoraInsertDto.getInsi());
		editoraBd.setNome(editoraInsertDto.getNome());
		return editoraRepository.save(editoraBd); //retornar editora inserir
	}

	@Override
	@Transactional
	public Editora atualizar(Long id, EditoraUpdateDto editoraUpdateDto) {
		Editora editoraBd = editoraRepository.findById(id).orElseThrow(() -> new EditoraNaoEncontradaException("Editora de id '"+id+"' não encontrada"));
		editoraBd.setInsi(editoraUpdateDto.getInsi());
		editoraBd.setNome(editoraUpdateDto.getNome());
		return editoraRepository.save(editoraBd);
	}

	@Override
	@Transactional
	public void inativar(Long id) {
		//1o verifica se existe
		Editora editoraBd = editoraRepository.findById(id).orElseThrow(() -> new EditoraNaoEncontradaException("Editora de id '"+id+"' não encontrada"));
		//verifica se esta atualmente ativo
		  //lanca um erro EditoraJaInativa exception, se não estiver ativo
		if(!editoraRepository.estaAtiva(id)) {
			throw new EditoraJaInativaException("A editora de id '"+id+"' já está inativa");
		}
		editoraBd.setAtivo(false);
		editoraRepository.save(editoraBd);
	}
	
	@Override
	@Transactional
	public void reativar(Long id) {
		//1o verifica se existe
		Editora editoraBd = editoraRepository.findById(id).orElseThrow(() -> new EditoraNaoEncontradaException("Editora de id '"+id+"' não encontrada"));
		//verifica se esta atualmente inativo
		  //lanca um erro EditoraJaAtiva exception, se não estiver inativo
		if(editoraRepository.estaAtiva(id)) {
			throw new EditoraJaAtivaException("A editora de id '"+id+"' já está ativa");
		}
		editoraBd.setAtivo(false);
		editoraRepository.save(editoraBd);
	}

	@Override
	public Iterable<Editora> buscarPorNome(String nome) {
		// TODO Auto-generated method stub
		return editoraRepository.findEditoraByNome(nome);
	}

	@Override
	public Iterable<Editora> buscarTodas(String nome) {
		// TODO Auto-generated method stub
		return editoraRepository.findAll();
	}

}
