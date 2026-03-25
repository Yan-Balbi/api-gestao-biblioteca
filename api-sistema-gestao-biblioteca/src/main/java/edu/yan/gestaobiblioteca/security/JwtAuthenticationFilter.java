package edu.yan.gestaobiblioteca.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import edu.yan.gestaobiblioteca.service.implementations.JwtServiceImplementation;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtServiceImplementation jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(
    	JwtServiceImplementation jwtService,
        UserDetailsService userDetailsService,
        HandlerExceptionResolver handlerExceptionResolver
    ) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }
    
	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain
	) throws ServletException, IOException {
		//acessando o campo authorization do JSON request
		String cabecalhoAutenticacao = request.getHeader("Authorization");
		
        if (cabecalhoAutenticacao == null || !cabecalhoAutenticacao.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        
        try {
            final String jwt = cabecalhoAutenticacao.substring(7);
            final String emailUsuario = jwtService.extrairUsername(jwt);
            
            Authentication autenticacao = SecurityContextHolder.getContext().getAuthentication();
            
            if (emailUsuario != null && autenticacao == null) {
            	UserDetails detalhesUsuario = this.userDetailsService.loadUserByUsername(emailUsuario);
                if (jwtService.tokenValido(jwt, detalhesUsuario)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    		detalhesUsuario,
                            null,
                            detalhesUsuario.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            
		} catch (Exception e) {
			/*TODO: TRATAR ESSA EXCEÇÃO DPS*/
	        handlerExceptionResolver.resolveException(request, response, null, e);
	        return;
		}
		
	}

}
