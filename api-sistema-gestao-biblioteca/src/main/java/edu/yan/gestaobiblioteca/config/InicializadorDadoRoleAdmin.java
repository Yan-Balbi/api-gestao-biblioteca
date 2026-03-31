package edu.yan.gestaobiblioteca.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.yan.gestaobiblioteca.model.UsuarioModel;
import edu.yan.gestaobiblioteca.respository.UsuarioRepository;
import edu.yan.gestaobiblioteca.service.implementations.AuthenticationServiceImplementation;

@Configuration
public class InicializadorDadoRoleAdmin {
	@Bean
    CommandLineRunner inicializar(UsuarioRepository usuarioRepository, 
    		AuthenticationServiceImplementation authenticatioServiceImplementation, 
    		PasswordEncoder passwordEncoder) {
		return args -> {
			boolean existeAdmin = usuarioRepository.existsByPapel("ROLE_ADMIN");
			
			if (!existeAdmin) {
                UsuarioModel admin = new UsuarioModel();
                admin.setNomeUsuario("Administrador");
                admin.setEmail("admin-email@email.com");
                admin.setCpf("00000000000");
                admin.setSenha("#!segredo-admin-gestao-biblio!#");
				authenticatioServiceImplementation.inserirPrimeiroAdmin(admin);
			}
		};
	}
}
