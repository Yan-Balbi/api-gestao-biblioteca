package edu.yan.gestaobiblioteca.service.implementations;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import edu.yan.gestaobiblioteca.service.interfaces.IJwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImplementation implements IJwtService{

    @Value("${security.jwt.secret-key}")
    private String chaveSecreta;

    @Value("${security.jwt.expiration-time}")
    private long expiracaoJwt;
    
    private SecretKey getChaveLogIn() {
        byte[] bytesChave = Decoders.BASE64.decode(chaveSecreta);
        return Keys.hmacShaKeyFor(bytesChave);
    }
    
    /*
      Claim é basicamente um par chave-valor dentro do token
     
      {
		  "sub": "yan",
		  "role": "ADMIN",
		  "exp": 1700000000
	  }

		| Claim  | Valor      | Significado |
		| ------ | ---------- | ----------- |
		| `sub`  | "yan"      | usuário     |
		| `role` | "ADMIN"    | permissão   |
		| `exp`  | 1700000000 | expiração   |

     */
	public Claims extrairTodosClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getChaveLogIn())
                .build()
                .parseClaimsJws(token)
                .getBody();
	}
	
	/*
	 * claimResolver pode ser Claims::getSubject, 
	 * Claims::getExpiration, 
	 * Claims::getIssuedAt, 
	 * Claims::getIssuer, 
	 * Claims::getAudience, 
	 * Claims::getId
	 */
	@Override
	public <T> T extrairClaim(
			String token, 
			Function<Claims, T> claimsResolver /*função anônima chamada claimsResolver que recebe claims e retorna um objeto qualquer*/
	)	{
        final Claims claims = extrairTodosClaims(token);
        return claimsResolver.apply(claims);
	}
    
    
	@Override
	public String extrairUsername(String token) {
		return extrairClaim(token, Claims::getSubject);
	}
	
	private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getChaveLogIn(), SignatureAlgorithm.HS256)
                .compact();
    }

	@Override
	public String gerarToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		// TODO Auto-generated method stub
		return buildToken(extraClaims, userDetails, expiracaoJwt);
	}
	
	@Override
	public String gerarToken(UserDetails userDetails) {
		return gerarToken(new HashMap<>(), userDetails);
	}

	@Override
	public long getTempoExpiracao() {
		// TODO Auto-generated method stub
		return this.expiracaoJwt;
	}
	
    private Date extrairExpiracao(String token) {
        return extrairClaim(token, Claims::getExpiration);
    }
	
    private boolean tokenExpirado(String token) {
        return extrairExpiracao(token).before(new Date());
    }

	@Override
	public boolean tokenValido(String token, UserDetails userDetails) {
		final String username = extrairUsername(token);
        return (username.equals(userDetails.getUsername())) && !tokenExpirado(token);
	}

}
