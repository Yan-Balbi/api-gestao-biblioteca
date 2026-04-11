package edu.yan.gestaobiblioteca.service.interfaces;

import edu.yan.gestaobiblioteca.dto.editora.EditoraInsertDto;
import edu.yan.gestaobiblioteca.dto.editora.EditoraUpdateDto;
import edu.yan.gestaobiblioteca.model.Editora;

public interface IEditoraService {
	Editora inserir(EditoraInsertDto editoraInsertDto);
	
	Editora atualizar(Long id, EditoraUpdateDto editoraUpdateDto);
	
	void inativar(Long id);//deletar
	
	void reativar(Long id);//cancelar delete
	
	Iterable<Editora> buscarPorNome(String nome);
	
	Iterable<Editora> buscarTodas(String nome);
}
