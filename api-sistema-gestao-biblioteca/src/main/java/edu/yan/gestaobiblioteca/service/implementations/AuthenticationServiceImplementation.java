package edu.yan.gestaobiblioteca.service.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.yan.gestaobiblioteca.dto.usuario.LoginUsuarioDto;
import edu.yan.gestaobiblioteca.exception.CpfInvalidoException;
import edu.yan.gestaobiblioteca.exception.CpfJaCadastradoException;
import edu.yan.gestaobiblioteca.exception.EmailJaCadastradoException;
import edu.yan.gestaobiblioteca.model.UsuarioModel;
import edu.yan.gestaobiblioteca.respository.UsuarioRepository;
import edu.yan.gestaobiblioteca.service.interfaces.IAuthenticationService;

@Service
public class AuthenticationServiceImplementation implements IAuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImplementation(
    	UsuarioRepository usuarioRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    private static boolean cpfValido(String cpf) {
		//remover todos os '.' e todos os '-' do trem
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		
		//pegar os 9 primeiros numeros
		//	123456789
		List<Integer> listaNumerosCpf = new  ArrayList<Integer>();
		for(char caracter : cpf.substring(0,9).toCharArray()) {
			listaNumerosCpf.add(Character.getNumericValue(caracter));
		}
		
		//cada um dos 9 numeros deverão ser multiplicados por pesos de 10 até 2
		//	a*10  b*9  c*8  d*7  e*6  f*5  g*4  h*3  i*2
		//agora tem que somar todos esses numeros. (a=1, b=2, ...,i=9)
		//  10 + 18 + 24 + 28 + 30 + 30 + 28 + 16 + 18 = 210
		int soma = java.util.stream.IntStream.range(0, 9).map(i -> listaNumerosCpf.get(i) * (10 - i)).sum();
		//Agora aplique a formula: (210 * 10) / 11 e obtenha o resto
		int resto = soma%11;
		
		//Cálculo do Primeiro Dígito Verificador (j)
		//	se o resto for menor que 2
		if(resto < 2) {
			if(!(Character.getNumericValue(cpf.charAt(9)) == 0)) return false;
		} else {
			if(!(Character.getNumericValue(cpf.charAt(9)) == 11-resto)) return false;
		}
		
		return true;
    }

    private UsuarioModel signup(UsuarioModel usuarioModel) {
		if(!cpfValido(usuarioModel.getCpf())) {
			throw new CpfInvalidoException("O CPF '"+usuarioModel.getCpf()+"' informado não é válido");
		}
		if(!usuarioRepository.findUsuarioByCpf(usuarioModel.getCpf()).isEmpty()) {
			throw new CpfJaCadastradoException("O CPF '"+usuarioModel.getCpf()+"'informado já está em uso");
		}
		if(!usuarioRepository.findUsuarioByEmail(usuarioModel.getEmail()).isEmpty()) {
			throw new EmailJaCadastradoException("O email '"+usuarioModel.getEmail()+"' informado já está uso");
		}
		
    	//encryptando a senha pra não expor-la no bd
    	usuarioModel.setSenha(passwordEncoder.encode(usuarioModel.getPassword()));
        return usuarioRepository.save(usuarioModel);
    }

	@Override
	public UsuarioModel signupAdmin(UsuarioModel usuarioModel) {
		usuarioModel.setPapel("ROLE_ADMIN");
		return signup(usuarioModel);
	}
    
	@Override
	public UsuarioModel signupBibliotecario(UsuarioModel usuarioModel) {
		usuarioModel.setPapel("ROLE_BIBLIOTECARIO");
		return signup(usuarioModel);
	}
	
	@Override
	public UsuarioModel signupCliente(UsuarioModel usuarioModel) {
		usuarioModel.setPapel("ROLE_CLIENTE");
		return signup(usuarioModel);
	}

    @Override
    public UsuarioModel authenticate(LoginUsuarioDto input) {
        try {
	    	authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        input.getEmail(),
	                        input.getSenha()
	                )
	        );
        } catch (Exception e) {
            System.out.println("ERRO REAL:");
            e.printStackTrace();
            throw e;
        }
        
        return usuarioRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }

}
