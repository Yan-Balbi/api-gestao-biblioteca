package edu.yan.gestaobiblioteca.service.implementations;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.yan.gestaobiblioteca.model.Suspensao;
import edu.yan.gestaobiblioteca.model.Usuario;
import edu.yan.gestaobiblioteca.respository.SuspensaoRepository;
import edu.yan.gestaobiblioteca.respository.UsuarioRepository;
import edu.yan.gestaobiblioteca.service.interfaces.ISuspensaoService;

@Service
public class SuspensaoUsuarioServiceImplementation implements ISuspensaoService{
	
	SuspensaoRepository suspensaoRepository;
	
	UsuarioRepository usuarioRepository;
	
	public SuspensaoUsuarioServiceImplementation(SuspensaoRepository suspensaoRepository, UsuarioRepository usuarioRepository) {
		this.suspensaoRepository = suspensaoRepository;
		this.usuarioRepository = usuarioRepository;
	}
	
	private Suspensao suspenderUsuario(Usuario usuario) {
		
		//se o usuário já tiver uma suspensao em andamento
			// retornar null
		if(suspensaoRepository.usuarioTemSuespensaoEmAndamento(usuario.getId())) {
			return null;
		}
		
		LocalDate dataFim = LocalDate.now().plusDays(14);
		
		Suspensao suspensao = new Suspensao();
		suspensao.setUsuario(usuario);
		suspensao.setDataFim(dataFim);
		
		return suspensaoRepository.save(suspensao);
	}
	
	@Override
	public void suspenderUsarios() {
		//TODO:IMPLEMENTAR DEPOIS DE CRIAR OS EMPRESTIMOS
		//obter todos os usuários ATIVOS que NÃO ESTÃO SUSPENSOS e ESTÃO COM EMPRESTIMO ATRASADO
		/*List<Usuario> listaUsuariosComEmprestimoAtrasado = (List<Usuario>) usuarioRepository.findTodosUsuariosAtivosComEmprestimoAtrasado();
		
		//para cada um desses usuários, mandar fazer a suspensão.
		for(Usuario u : listaUsuariosComEmprestimoAtrasado) {
			//suspenderUsuario(u);
		}
		*/
	}
}
