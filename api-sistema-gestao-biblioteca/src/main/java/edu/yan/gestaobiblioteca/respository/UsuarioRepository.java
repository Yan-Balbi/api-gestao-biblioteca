package edu.yan.gestaobiblioteca.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.yan.gestaobiblioteca.model.Usuario;


public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	//obs: do jeito que tá vai listar todos os usuarios: admins, employees e clientes
	Optional<Usuario> findById(Long id);
	
	Optional<Usuario> findByCpf(String cpf);
	
	Optional<Usuario> findByEmail(String email);
	
	Optional<Usuario> findByEmailAndDeletedAtIsNull(String email);
	
    Optional<Usuario> findByPapel(String papel);

    boolean existsByPapel(String papel);
	
	boolean existsByEmailAndDeletedAtIsNotNull(String email);
	
	@Query("SELECT u FROM Usuario u WHERE u.cpf = :usuarioCpf AND u.deletedAt IS NULL")
	Optional<Usuario> findUsuarioByCpf(@Param("usuarioCpf") String cpf);
	
	@Query("SELECT u FROM Usuario u WHERE u.email = :usuarioEmail AND u.deletedAt IS NULL")
	Optional<Usuario> findUsuarioByEmail(@Param("usuarioEmail") String email);

	/*================== Buscar por CPF ==================*/
	//Query personalizada para filtrar por role admin
	@Query("SELECT u FROM Usuario u WHERE u.papel = 'ROLE_ADMIN' AND u.cpf = :adminCpf AND u.deletedAt IS NULL")
	Optional<Usuario> findAdminByCpf(@Param("adminCpf") String cpf);
	
	//Query personalizada para filtrar por role bibliotecario
	@Query("SELECT u FROM Usuario u WHERE u.papel = 'ROLE_BIBLIOTECARIO' AND u.cpf = :bibliotecarioCpf AND u.deletedAt IS NULL")
	Optional<Usuario> findBibliotecarioByCpf(@Param("bibliotecarioCpf") String cpf);
	
	//Query personalizada para filtrar por role cliente
	@Query("SELECT u FROM Usuario u WHERE u.papel = 'ROLE_CLIENTE' AND u.cpf = :clienteCpf AND u.deletedAt IS NULL")
	Optional<Usuario> findClienteByCpf(@Param("clienteCpf") String cpf);
	
	/*================== Buscar por Id ==================*/
	//Query personalizada para filtrar por role admin
	@Query("SELECT u FROM Usuario u WHERE u.papel = 'ROLE_ADMIN' AND u.id = :adminId AND u.deletedAt IS NULL")
	Optional<Usuario> findAdminById(@Param("adminId") Long id);
	
	//Query personalizada para filtrar por role bibliotecario
	@Query("SELECT u FROM Usuario u WHERE u.papel = 'ROLE_BIBLIOTECARIO' AND u.cpf = :bibliotecarioId AND u.deletedAt IS NULL")
	Optional<Usuario> findBibliotecarioById(@Param("bibliotecarioId") Long id);
	
	//Query personalizada para filtrar por role cliente
	@Query("SELECT u FROM Usuario u WHERE u.papel = 'ROLE_CLIENTE' AND u.cpf = :clienteId AND u.deletedAt IS NULL")
	Optional<Usuario> findClienteById(@Param("clienteId") Long id);
	
	/*================== Buscar por Id ==================*/
	//Query personalizada para filtrar por role admin
	@Query("SELECT u FROM Usuario u WHERE u.papel = 'ROLE_ADMIN' AND u.nomeUsuario = :adminNome AND u.deletedAt IS NULL")
	Iterable<Usuario> findAdminByNome(@Param("adminNome") String nome);
	
	//Query personalizada para filtrar por role bibliotecario
	@Query("SELECT u FROM Usuario u WHERE u.papel = 'ROLE_BIBLIOTECARIO' AND u.nomeUsuario = :bibliotecarioNome AND u.deletedAt IS NULL")
	Iterable<Usuario> findBibliotecarioByNome(@Param("bibliotecarioNome") String nome);
	
	//Query personalizada para filtrar por role cliente
	@Query("SELECT u FROM Usuario u WHERE u.papel = 'ROLE_CLIENTE' AND u.nomeUsuario = :clienteNome AND u.deletedAt IS NULL")
	Iterable<Usuario> findClienteByNome(@Param("clienteNome") String nome);
	
	/*================== Buscas gerais ==================*/
	@Query("SELECT u FROM Usuario u WHERE u.papel = 'ROLE_ADMIN' AND u.deletedAt IS NULL")
	Iterable<Usuario> findTodosAdminsAtivos();
	
	@Query("SELECT u FROM Usuario u WHERE u.papel = 'ROLE_BIBLIOTECARIO' AND u.deletedAt IS NULL")
	Iterable<Usuario> findTodosBibliotecariosAtivos();

	@Query("SELECT u FROM Usuario u WHERE u.papel = 'ROLE_CLIENTE' AND u.deletedAt IS NULL")
	Iterable<Usuario> findTodosClientesAtivos();
	
	//TODO:IMPLEMENTAR DEPOIS DE CRIAR OS EMPRESTIMOS
	//@Query("SELECT u FROM Usuario u JOIN emprestimo e ON u.id = e.usuario_id WHERE u.papel = 'ROLE_CLIENTE' AND u.deletedAt IS NULL AND NOW <= e.")
	//Iterable<Usuario> findTodosUsuariosAtivosComEmprestimoAtrasado();
	
	@Query("""
		    SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END
		    FROM Usuario u
		    WHERE u.cpf = :cpf AND u.deletedAt IS NULL
		   """)
	boolean haUsuarioAtivoComOCpf(@Param("cpf") String cpf);
}
