package edu.yan.gestaobiblioteca.service.interfaces;

import edu.yan.gestaobiblioteca.dto.usuario.LoginUsuarioDto;
import edu.yan.gestaobiblioteca.model.UsuarioModel;

public interface IAuthenticationService {
	UsuarioModel signupAdmin(UsuarioModel usuarioModel);
	
	UsuarioModel signupBibliotecario(UsuarioModel usuarioModel);

	UsuarioModel signupCliente(UsuarioModel usuarioModel);
	
    UsuarioModel authenticate(LoginUsuarioDto input);
}
