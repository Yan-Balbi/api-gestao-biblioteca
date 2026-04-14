package edu.yan.gestaobiblioteca.service.interfaces;

import edu.yan.gestaobiblioteca.dto.usuario.LoginUsuarioDto;
import edu.yan.gestaobiblioteca.dto.usuario.UsuarioInsertDto;
import edu.yan.gestaobiblioteca.model.Usuario;

public interface IAuthenticationService {
	Usuario signupAdmin(UsuarioInsertDto usuarioModel);
	
	Usuario signupBibliotecario(UsuarioInsertDto usuarioModel);

	Usuario signupCliente(UsuarioInsertDto usuarioModel);
	
    Usuario authenticate(LoginUsuarioDto input);
}
