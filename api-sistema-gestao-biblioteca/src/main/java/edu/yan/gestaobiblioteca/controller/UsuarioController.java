package edu.yan.gestaobiblioteca.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.yan.gestaobiblioteca.dto.usuario.LoginResposta;
import edu.yan.gestaobiblioteca.dto.usuario.LoginUsuarioDto;
import edu.yan.gestaobiblioteca.dto.usuario.UsuarioInsertDto;
import edu.yan.gestaobiblioteca.dto.usuario.UsuarioUpdateDTO;
import edu.yan.gestaobiblioteca.model.Usuario;
import edu.yan.gestaobiblioteca.service.implementations.AuthenticationServiceImplementation;
import edu.yan.gestaobiblioteca.service.implementations.JwtServiceImplementation;
import edu.yan.gestaobiblioteca.service.implementations.UsuarioServiceImplementation;
import jakarta.validation.Valid;

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
	public ResponseEntity<Usuario> inserirCliente(@RequestBody @Valid UsuarioInsertDto usuarioRequest){
		
		Usuario usuarioCriado = authenticatioServiceImplementation.signupCliente(usuarioRequest);
		
		URI local = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioCriado.getId()).toUri();
		
		return ResponseEntity.created(local).body(usuarioCriado);
	}
	
	@PostMapping("/auth/cliente-login")
	public ResponseEntity<LoginResposta> login(@RequestBody @Valid LoginUsuarioDto loginUsuarioDto){
		
        Usuario authenticatedUser = authenticatioServiceImplementation.authenticate(loginUsuarioDto);

        String jwtToken = jwtServiceImplementation.gerarToken(authenticatedUser);

        LoginResposta loginResponse = new LoginResposta();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiraEm(jwtServiceImplementation.getTempoExpiracao());

        return ResponseEntity.ok(loginResponse);
	}
	
	//authentication é equivalente a fazer Authentication authentication = SecurityContextHolder.getContext().getAuthentication()
	//quando eu faço authentication.getPrincipal, eu obtenho o usuário, ou seja Usuario user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	// então, a consulta seria algo como: Long id = SecurityContextHolder.getContext().getAuthentication().getPrincipal().getId();
	@DeleteMapping("/cliente/{usuarioId}")
	@PreAuthorize("#usuarioId == authentication.principal.id or hasRole('ADMIN')")
	public ResponseEntity<Void> deletarCliente(@PathVariable Long usuarioId){
		usuarioImplementationService.deletar(usuarioId);	
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{usuarioId}")
	@PreAuthorize("#usuarioId == authentication.principal.id")
	public ResponseEntity<Usuario> atualizarRegra(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioUpdateDTO usuarioUpdateDto) {
		Usuario usuario = usuarioImplementationService.atualizar(usuarioId, usuarioUpdateDto);
		return ResponseEntity.ok(usuario);
	}
	
    @GetMapping("/home")
    public String home(){
        return "home";
    }

}
