package edu.yan.gestaobiblioteca.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.yan.gestaobiblioteca.handler.UsuarioNaoEncontrado;
import edu.yan.gestaobiblioteca.respository.UsuarioRepository;

@Configuration
public class ApplicationConfiguration {
	private final UsuarioRepository usuarioRepository;
	
    public ApplicationConfiguration(UsuarioRepository userRepository) {
        this.usuarioRepository = userRepository;
    }
    

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            public UserDetails loadUserByUsername(String username) throws UsuarioNaoEncontrado {
                // username = o que o usuário digitou na tela de login
                return usuarioRepository.findByEmail(username) // aqui você decide buscar por email
                        .orElseThrow(() -> new UsuarioNaoEncontrado());
            }
        };
    }
	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
    	return config.getAuthenticationManager();
    }
    
    @Bean
    AuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    	
    	authProvider.setUserDetailsService(userDetailsService());
    	authProvider.setPasswordEncoder(passwordEncoder());
    	
    	return authProvider;
    }
}
