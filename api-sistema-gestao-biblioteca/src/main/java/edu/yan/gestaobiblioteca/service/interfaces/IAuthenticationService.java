package edu.yan.gestaobiblioteca.service.interfaces;

import edu.yan.gestaobiblioteca.dto.usuario.LoginUsuarioDto;
import edu.yan.gestaobiblioteca.model.Usuario;

public interface IAuthenticationService {
	Usuario signupAdmin(Usuario usuarioModel);
	
	Usuario signupBibliotecario(Usuario usuarioModel);

	Usuario signupCliente(Usuario usuarioModel);
	
    Usuario authenticate(LoginUsuarioDto input);
}
