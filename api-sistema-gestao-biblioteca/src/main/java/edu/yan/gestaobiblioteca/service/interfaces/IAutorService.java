package edu.yan.gestaobiblioteca.service.interfaces;

import java.util.Optional;

import edu.yan.gestaobiblioteca.dto.autor.AutorFiltroDto;
import edu.yan.gestaobiblioteca.dto.autor.AutorPseudonimoInsertDto;
import edu.yan.gestaobiblioteca.dto.autor.AutorPseudonimoUpdateDto;
import edu.yan.gestaobiblioteca.dto.autor.AutorVerdadeiroInsertDto;
import edu.yan.gestaobiblioteca.dto.autor.AutorVerdadeiroUpdateDto;
import edu.yan.gestaobiblioteca.dto.autor.VincularAutorVerdadeiroDto;
import edu.yan.gestaobiblioteca.model.Autor;

public interface IAutorService {
	
	public Autor inserirAutorVerdadeiro(AutorVerdadeiroInsertDto autor);
	
	public Autor inserirAutorPseudonimo(Long autorVerdadeiroid, AutorPseudonimoInsertDto autor);
	
	public Autor inserirAutorPseudonimo(AutorPseudonimoInsertDto autor);
	
	public Iterable<Autor> buscarPorFiltro(AutorFiltroDto filtro);
	
	public Autor atualizarAutorVerdadeiro(Long id, AutorVerdadeiroUpdateDto dto);
	
	public Autor atualizarAutorPseudonimo(Long pseudonimoId, AutorPseudonimoUpdateDto dto);
	
	public Autor vincularAutorVerdadeiro(Long pseudonimoId, Long autorVerdadeiroid);
	
	public Autor desvincularAutorVerdadeiro(Long pseudonimoId);
	
	//public Iterable<Autor> buscarAutoresVerdadeiros();
	
	//public Iterable<Autor> buscarAutoresPseudonimos();
	
	//public Iterable<Autor> buscarAutorPorNome();
	
	public Optional<Autor> buscarAutorPorId(Long id);
	
	
}
