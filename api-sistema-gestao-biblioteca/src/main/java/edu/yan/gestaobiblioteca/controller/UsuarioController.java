package edu.yan.gestaobiblioteca.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.yan.gestaobiblioteca.model.UsuarioModel;
import edu.yan.gestaobiblioteca.service.implementations.UsuarioServiceImplementation;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
	@Autowired
	private UsuarioServiceImplementation usuarioImplementationService;
	
	@PostMapping("/cliente")
	public ResponseEntity<UsuarioModel> inserirCliente(@RequestBody UsuarioModel usuarioRequest){
		UsuarioModel usuarioCriado = usuarioImplementationService.inserirUsuario(usuarioRequest);
		
		URI local = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioCriado.getId()).toUri();
		
		return ResponseEntity.created(local).body(usuarioCriado);
	}
	
    @GetMapping("/home")
    public String home(){
        return "ola mundo";
    }

}
