package edu.yan.gestaobiblioteca.respository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.yan.gestaobiblioteca.model.Suspensao;

public interface SuspensaoRepository extends CrudRepository<Suspensao, Long>{
	
	@Query("""
		    SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END
		    FROM Suspensao s
		    WHERE s.usuario.id = :usuarioId
		      AND s.dataInicio <= CURRENT_TIMESTAMP
		      AND (s.dataFim IS NULL OR s.dataFim >= CURRENT_TIMESTAMP)
		   """)
	boolean usuarioTemSuespensaoEmAndamento(@Param("usuarioId") Long usuarioId);
}
