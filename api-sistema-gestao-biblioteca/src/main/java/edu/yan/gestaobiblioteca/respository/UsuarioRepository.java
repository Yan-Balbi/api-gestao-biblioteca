package edu.yan.gestaobiblioteca.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.yan.gestaobiblioteca.model.UsuarioModel;


public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long>{
	//obs: do jeito que tá vai listar todos os usuarios: admins, employees e clientes
	Optional<UsuarioModel> findByCpf(String cpf);
	
	Optional<UsuarioModel> findByEmail(String email);

	/*================== Buscar por CPF ==================*/
	//Query personalizada para filtrar por role admin
	@Query("SELECT u FROM UsuarioModel u WHERE u.papel = 'ROLE_ADMIN' AND u.cpf = :adminCpf")
	Optional<UsuarioModel> findAdminByCpf(@Param("adminCpf") String cpf);
	
	//Query personalizada para filtrar por role bibliotecario
	@Query("SELECT u FROM UsuarioModel u WHERE u.papel = 'ROLE_BIBLIOTECARIO' AND u.cpf = :bibliotecarioCpf")
	Optional<UsuarioModel> findBibliotecarioByCpf(@Param("bibliotecarioCpf") String cpf);
	
	//Query personalizada para filtrar por role cliente
	@Query("SELECT u FROM UsuarioModel u WHERE u.papel = 'ROLE_CLIENTE' AND u.cpf = :clienteCpf")
	Optional<UsuarioModel> findClienteByCpf(@Param("clienteCpf") String cpf);
	
	/*================== Buscar por Id ==================*/
	//Query personalizada para filtrar por role admin
	@Query("SELECT u FROM UsuarioModel u WHERE u.papel = 'ROLE_ADMIN' AND u.id = :adminId")
	Optional<UsuarioModel> findAdminById(@Param("adminId") Long id);
	
	//Query personalizada para filtrar por role bibliotecario
	@Query("SELECT u FROM UsuarioModel u WHERE u.papel = 'ROLE_BIBLIOTECARIO' AND u.cpf = :bibliotecarioId")
	Optional<UsuarioModel> findBibliotecarioById(@Param("bibliotecarioId") Long id);
	
	//Query personalizada para filtrar por role cliente
	@Query("SELECT u FROM UsuarioModel u WHERE u.papel = 'ROLE_CLIENTE' AND u.cpf = :clienteId")
	Optional<UsuarioModel> findClienteById(@Param("clienteId") Long id);
	
	/*================== Buscar por Id ==================*/
	//Query personalizada para filtrar por role admin
	@Query("SELECT u FROM UsuarioModel u WHERE u.papel = 'ROLE_ADMIN' AND u.nomeUsuario = :adminNome")
	Iterable<UsuarioModel> findAdminByNome(@Param("adminNome") String nome);
	
	//Query personalizada para filtrar por role bibliotecario
	@Query("SELECT u FROM UsuarioModel u WHERE u.papel = 'ROLE_BIBLIOTECARIO' AND u.nomeUsuario = :bibliotecarioNome")
	Iterable<UsuarioModel> findBibliotecarioByNome(@Param("bibliotecarioNome") String nome);
	
	//Query personalizada para filtrar por role cliente
	@Query("SELECT u FROM UsuarioModel u WHERE u.papel = 'ROLE_CLIENTE' AND u.nomeUsuario = :clienteNome")
	Iterable<UsuarioModel> findClienteByNome(@Param("clienteNome") String nome);
	
	/*================== Buscas gerais ==================*/
	@Query("SELECT u FROM UsuarioModel u WHERE u.papel = 'ROLE_ADMIN'")
	Iterable<UsuarioModel> findTodosAdmins();
	
	@Query("SELECT u FROM UsuarioModel u WHERE u.papel = 'ROLE_BIBLIOTECARIO'")
	Iterable<UsuarioModel> findTodosBibliotecarios();

	@Query("SELECT u FROM UsuarioModel u WHERE u.papel = 'ROLE_CLIENTE'")
	Iterable<UsuarioModel> findTodosClientes();
}
