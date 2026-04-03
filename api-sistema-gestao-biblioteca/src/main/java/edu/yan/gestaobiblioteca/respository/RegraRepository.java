package edu.yan.gestaobiblioteca.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.yan.gestaobiblioteca.model.Regra;

public interface RegraRepository extends CrudRepository<Regra, Long>{
	
	/*
	CREATE FUNCTION usuarioEstaSuspenso (usuario_id_inserido INT)
	RETURN BOOLEAN
	
	BEGIN
	  DECLARE resultado BOOLEAN
	  SELECT EXISTS (
	    SELECT * FROM suspensoes
	    WHERE usuario_id = usuario_id_inserido
	    AND NOW() BETWEEN data_inicio AND data_fim
	  ) INTO resultado;
	
	  RETURN resultado;
	END
	 */
	@Query("""
		    SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END
		    FROM Suspensao s
		    WHERE s.usuario.id = :usuarioId
		      AND s.dataInicio <= CURRENT_TIMESTAMP
		      AND (s.dataFim IS NULL OR s.dataFim >= CURRENT_TIMESTAMP)
		""")
	boolean usuarioEstaAtualmenteSuspenso(@Param("usuarioId") Long id);// isso aqui era pra ser usado em //emprestimos, não aqui
	
	Optional<Regra> findRegraById(Long id);
	
	/*
	 CREATE FUNCTION ha_regra_inserida ()
	 RETURN BOOLEAN
	 
	 BEGIN
	   DECLARE resultado BOOLEAN
	   SELECT EXISTS (
	     SELECT * FROM regras
	   ) INTO resultado;
	   RETURN resultado;
	 END
	 */
	@Query("""
			SELECT CASE WHEN COUNT(rg) > 0 THEN true ELSE false END
			FROM Regra rg
		   """)
	boolean haRegraInserida();
}
