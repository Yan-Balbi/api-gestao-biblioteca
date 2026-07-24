package edu.yan.gestaobiblioteca.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.yan.gestaobiblioteca.model.Autor;

//usar especification para fazer filtragem mais customizada de autores

/*
>  permitr pesquisar autores por:
	Nome (prenome ou sobrenome)
	Ser pseudônimo ou não
	Data de nascimento
	Possuir autor verdadeiro associado, em caso de pseudonimos sem autor verdadeiro vinculado
	etc.
 */
public interface AutorRepository extends CrudRepository<Autor, Long>, JpaSpecificationExecutor<Autor> {
	
	//buscar todos autores
	//Iterable<Autor> findAllAutor();
	
	//buscar todos autores verdadeiros
	@Query("SELECT a FROM Autor a WHERE a.anonimo = false")
	Iterable<Autor> findAllAutoresVerdadeiros();
	
	//buscar todos autores pseudonimos
	@Query("SELECT a FROM Autor a WHERE a.anonimo = true")
	Iterable<Autor> findAllAutoresPseudonimos();
	
	//buscar autor por id
	//Optional<Autor> findAutorById(int id);
	
	//buscar autor por nome
	@Query("""
		    SELECT a
		    FROM Autor a
		    WHERE LOWER(a.prenome) LIKE LOWER(CONCAT('%', :nome, '%'))
		       OR LOWER(a.sobrenome) LIKE LOWER(CONCAT('%', :nome, '%'))
		""")
	Iterable<Autor> buscarPorNome(@Param("nome") String nome);
	
    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.pseudonimos WHERE a.id = :id")
    Optional<Autor> findByIdComPseudonimos(@Param("id") Long id);
    
    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.autorVerdadeiro WHERE a.id = :id")
    Optional<Autor> findByIdComAutorVerdadeiro(@Param("id") Long id);
}
