package edu.yan.gestaobiblioteca.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.yan.gestaobiblioteca.model.Editora;

public interface EditoraRepository extends CrudRepository<Editora, Long> {
	
	@Query("SELECT e FROM editora e WHERE e.insi = :insiInserido AND e.ativo = true")
	Optional<Editora> findEditoraByInsi(@Param("insiInserido") String insi);
	
	@Query("SELECT e FROM editora e WHERE e.nome LIKE %:nomeInserido% AND e.ativo = true")
	Iterable<Editora> findEditoraByNome(@Param("nomeInserido") String nome);
	
	Optional<Editora> findEditoraById();

	@Query("SELECT e.ativa FROM editora e WHERE e.id = :editoraId")
	boolean estaAtiva(@Param("editoraId") Long id);
}
