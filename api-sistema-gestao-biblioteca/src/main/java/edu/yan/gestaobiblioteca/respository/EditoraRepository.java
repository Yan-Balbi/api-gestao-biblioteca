package edu.yan.gestaobiblioteca.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.yan.gestaobiblioteca.model.Editora;

public interface EditoraRepository extends CrudRepository<Editora, Long> {
	
	@Query("SELECT e FROM Editora e WHERE e.nome LIKE %:nomeInserido% AND e.ativo = true")
	Iterable<Editora> findEditoraByNome(@Param("nomeInserido") String nome);
	
	@Query("SELECT e FROM Editora e WHERE e.id = :editoraId AND e.ativo = true")
	Optional<Editora> findEditoraAtivaById(@Param("editoraId") Long id);

	@Query("SELECT e.ativo FROM Editora e WHERE e.id = :editoraId")
	boolean estaAtiva(@Param("editoraId") Long id);
}
