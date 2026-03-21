package edu.yan.gestaobiblioteca.service.interfaces;

import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface IJwtService {
    String extrairUsername(String token);
    
    <T> T extrairClaim(String token, Function<Claims, T> claimsResolver);
    
    String gerarToken(UserDetails userDetails);
    
    String gerarToken(Map<String, Object> extraClaims, UserDetails userDetails);
    
    long getTempoExpiracao();
    
    boolean tokenValido(String token, UserDetails userDetails);
}
