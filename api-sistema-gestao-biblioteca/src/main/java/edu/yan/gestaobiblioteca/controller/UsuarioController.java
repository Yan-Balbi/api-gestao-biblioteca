package edu.yan.gestaobiblioteca.controller;

import java.net.URI;


import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.yan.gestaobiblioteca.dto.usuario.LoginResposta;
import edu.yan.gestaobiblioteca.dto.usuario.LoginUsuarioDto;
import edu.yan.gestaobiblioteca.model.UsuarioModel;
import edu.yan.gestaobiblioteca.service.implementations.AuthenticationServiceImplementation;
import edu.yan.gestaobiblioteca.service.implementations.JwtServiceImplementation;
import edu.yan.gestaobiblioteca.service.implementations.UsuarioServiceImplementation;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
	@Autowired
	private UsuarioServiceImplementation usuarioImplementationService;
	
	@Autowired
	private AuthenticationServiceImplementation authenticatioServiceImplementation;
	
	@Autowired
	private JwtServiceImplementation jwtServiceImplementation;
	
	@PostMapping("/auth/cliente-signup")
	public ResponseEntity<UsuarioModel> inserirCliente(@RequestBody UsuarioModel usuarioRequest){
		
		UsuarioModel usuarioCriado = authenticatioServiceImplementation.signupCliente(usuarioRequest);
		
		URI local = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioCriado.getId()).toUri();
		
		return ResponseEntity.created(local).body(usuarioCriado);
	}
	
	@PostMapping("/auth/cliente-login")
	public ResponseEntity<LoginResposta> login(@RequestBody LoginUsuarioDto loginUsuarioDto){
		
        UsuarioModel authenticatedUser = authenticatioServiceImplementation.authenticate(loginUsuarioDto);

        String jwtToken = jwtServiceImplementation.gerarToken(authenticatedUser);

        LoginResposta loginResponse = new LoginResposta();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiraEm(jwtServiceImplementation.getTempoExpiracao());

        return ResponseEntity.ok(loginResponse);
	}
	
	@DeleteMapping("/cliente/{usuarioId}")
	public ResponseEntity<Void> deletarCliente(@PathVariable Long usuarioId){
		System.out.println("CHEGOU NO DELETE");
		usuarioImplementationService.deletarUsuario(usuarioId);	
		return ResponseEntity.noContent().build();
	}
	
	
    @GetMapping("/home")
    public String home(){
        return "home";
    }

}
