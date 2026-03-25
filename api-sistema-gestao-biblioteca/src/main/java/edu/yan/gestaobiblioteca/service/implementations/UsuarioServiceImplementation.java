package edu.yan.gestaobiblioteca.service.implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.yan.gestaobiblioteca.dto.usuario.UsuarioUpdateDTO;
import edu.yan.gestaobiblioteca.exception.CpfInvalidoException;
import edu.yan.gestaobiblioteca.exception.RegraDeNegocioException;
import edu.yan.gestaobiblioteca.exception.CpfJaCadastradoException;
import edu.yan.gestaobiblioteca.exception.EmailJaCadastradoException;
import edu.yan.gestaobiblioteca.exception.UsuarioNaoEncontrado;
import edu.yan.gestaobiblioteca.model.UsuarioModel;
import edu.yan.gestaobiblioteca.respository.UsuarioRepository;
import edu.yan.gestaobiblioteca.service.interfaces.IUsuarioService;
import jakarta.transaction.Transactional;

@Service
public class UsuarioServiceImplementation implements IUsuarioService{
/* 	alterato
	@Autowired
	UsuarioRepository usuarioRepository;
*/

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImplementation(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
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
    
/*	private UsuarioModel inserirUsuario(UsuarioModel usuarioModel) {
		if(!cpfValido(usuarioModel.getCpf())) {
			throw new CpfInvalidoException("O CPF '"+usuarioModel.getCpf()+"' informado não é válido");
		}
		if(!usuarioRepository.findUsuarioByCpf(usuarioModel.getCpf()).isEmpty()) {
			throw new CpfJaCadastradoException("O CPF '"+usuarioModel.getCpf()+"'informado já está em uso");
		}
		if(!usuarioRepository.findUsuarioByEmail(usuarioModel.getEmail()).isEmpty()) {
			throw new EmailJaCadastradoException("O email '"+usuarioModel.getEmail()+"' informado já está uso");
		}

		UsuarioModel usuarioInserido = usuarioRepository.save(usuarioModel);
		return usuarioInserido;
	}
*/	
/*	@Override
	public UsuarioModel inserirAdmin(UsuarioModel usuarioModel) {
		usuarioModel.setPapel("ROLE_ADMIN");
		return inserirUsuario(usuarioModel);
	}
*/
	@Override
	@Transactional
	public UsuarioModel atualizarUsuario(Long id, UsuarioUpdateDTO usuarioUpdateDTO) {
		UsuarioModel usuarioBd = usuarioRepository.findAdminById(id).orElseThrow(()-> new UsuarioNaoEncontrado("Administrador de id '"+id+"' não encotrado"));
		usuarioBd.setCpf(usuarioUpdateDTO.getCpf());
		usuarioBd.setEmail(usuarioUpdateDTO.getEmail());
		usuarioBd.setNomeUsuario(usuarioUpdateDTO.getNomeUsuario());
		usuarioBd.setSenha(usuarioUpdateDTO.getSenha());
		return usuarioBd;
	}
	
	@Override
	@Transactional
	public void deletarUsuario(Long id) {
		UsuarioModel usuarioBd = usuarioRepository.findById(id).orElseThrow(()-> new UsuarioNaoEncontrado("Usuario de id '"+id+"' não encotrado"));
		usuarioBd.setDeletedAt(new Date());
	}

/*	@Override
	public UsuarioModel inserirBibliotecario(UsuarioModel usuarioModel) {
		usuarioModel.setPapel("ROLE_BIBLIOTECARIO");
		return inserirUsuario(usuarioModel);
	}
*/
	@Override
	public Optional<UsuarioModel> buscarBibliotecarioPorCpf(String cpf) {
		return usuarioRepository.findBibliotecarioByCpf(cpf);
	}

	@Override
	public Iterable<UsuarioModel> buscarBibliotecarioPorNome(String nome) {

		return usuarioRepository.findBibliotecarioByNome(nome);
	}

	@Override
	public Iterable<UsuarioModel> buscarTodosBibliotecarios() {
		
		return usuarioRepository.findTodosBibliotecarios();
	}

/*	@Override
	public UsuarioModel inserirCliente(UsuarioModel usuarioModel) {
		usuarioModel.setPapel("ROLE_CLIENTE");
		return inserirUsuario(usuarioModel);
	}
*/
	@Override
	public Optional<UsuarioModel> buscarClientePorCpf(String cpf) {
		return usuarioRepository.findClienteByCpf(cpf);
	}

	@Override
	public Iterable<UsuarioModel> buscarClientePorNome(String nome) {

		return usuarioRepository.findClienteByNome(nome);
	}

	@Override
	public Iterable<UsuarioModel> buscarTodosClientes() {

		return usuarioRepository.findTodosClientes();
	}

}
