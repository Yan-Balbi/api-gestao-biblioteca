package edu.yan.gestaobiblioteca.exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import edu.yan.gestaobiblioteca.exception.regra.DuracaoSuspensaoDeUsuarioInvalidaException;
import edu.yan.gestaobiblioteca.exception.regra.QuantidadeMaximaEmprestimosInvalidaException;
import edu.yan.gestaobiblioteca.exception.regra.RegraJaInseridaException;
import edu.yan.gestaobiblioteca.exception.regra.RegraNaoEncontrada;
import edu.yan.gestaobiblioteca.exception.regra.TempoDeExpiracaoDeAgendamentoInvalidaException;
import edu.yan.gestaobiblioteca.exception.regra.TempoDuracaoEmprestimoInvalidaException;
import edu.yan.gestaobiblioteca.exception.usuario.CpfInvalidoException;
import edu.yan.gestaobiblioteca.exception.usuario.CpfJaCadastradoException;
import edu.yan.gestaobiblioteca.exception.usuario.CredenciaisInvalidasException;
import edu.yan.gestaobiblioteca.exception.usuario.EmailJaCadastradoException;
import edu.yan.gestaobiblioteca.exception.usuario.UsuarioDeletadoException;
import edu.yan.gestaobiblioteca.exception.usuario.UsuarioDesativadoException;
import edu.yan.gestaobiblioteca.exception.usuario.UsuarioNaoEncontrado;



@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({
	    CpfInvalidoException.class,
	    CpfJaCadastradoException.class,
	    UsuarioNaoEncontrado.class,
	    EmailJaCadastradoException.class,
	    CredenciaisInvalidasException.class,
	    UsuarioDesativadoException.class,
	    UsuarioDeletadoException.class
	})
    public ProblemDetail handleUsuarioException(Exception exception) {
		ProblemDetail detalheErro = null;
		
		exception.printStackTrace();
		
		if(exception instanceof CpfInvalidoException) {
			detalheErro = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
			detalheErro.setDetail(exception.getMessage());
			detalheErro.setTitle("CPF Inválido.");
		}
		
		if(exception instanceof CpfJaCadastradoException) {
			detalheErro = ProblemDetail.forStatus(HttpStatus.CONFLICT);
			detalheErro.setDetail(exception.getMessage());
			detalheErro.setTitle("CPF já cadastrado.");
		}
		
		if(exception instanceof EmailJaCadastradoException) {
			detalheErro = ProblemDetail.forStatus(HttpStatus.CONFLICT);
			detalheErro.setDetail(exception.getMessage());
			detalheErro.setTitle("Email já cadastrado.");
		}
		
		if(exception instanceof UsuarioNaoEncontrado) {
			detalheErro = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
			detalheErro.setDetail(exception.getMessage());
			detalheErro.setTitle("Usuario não encontrado.");
		}
		
		if(detalheErro == null) {
			detalheErro = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			detalheErro.setDetail(exception.getMessage());
			detalheErro.setTitle("Erro interno do servidor desconhecido.");
		}
		
		if (exception instanceof CredenciaisInvalidasException) {
		    detalheErro = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
		    detalheErro.setDetail(exception.getMessage());
		    detalheErro.setTitle("Credenciais inválidas.");
		}

		if (exception instanceof UsuarioDesativadoException) {
		    detalheErro = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
		    detalheErro.setDetail(exception.getMessage());
		    detalheErro.setTitle("Usuário desativado.");
		}
		
		if (exception instanceof UsuarioDeletadoException) {
		    detalheErro = ProblemDetail.forStatus(HttpStatus.GONE);
		    detalheErro.setDetail(exception.getMessage());
		    detalheErro.setTitle("Usuário deletado.");
		}
		
		return detalheErro;
    }
	@ExceptionHandler({
	    RegraJaInseridaException.class,
	    RegraNaoEncontrada.class,
	    DuracaoSuspensaoDeUsuarioInvalidaException.class,
	    QuantidadeMaximaEmprestimosInvalidaException.class,
	    TempoDeExpiracaoDeAgendamentoInvalidaException.class,
	    TempoDuracaoEmprestimoInvalidaException.class
	})
    public ProblemDetail handleRegraException(Exception exception) {
		ProblemDetail detalheErro = null;
		
		exception.printStackTrace();
		
		if (exception instanceof DuracaoSuspensaoDeUsuarioInvalidaException) {
		    detalheErro = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		    detalheErro.setDetail(exception.getMessage());
		    detalheErro.setTitle("Duração Suspensão inválida.");
		}
		
		if (exception instanceof QuantidadeMaximaEmprestimosInvalidaException) {
		    detalheErro = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		    detalheErro.setDetail(exception.getMessage());
		    detalheErro.setTitle("Quantidade Máx. Emprestimos inválida.");
		}
		
		if (exception instanceof TempoDeExpiracaoDeAgendamentoInvalidaException) {
		    detalheErro = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		    detalheErro.setDetail(exception.getMessage());
		    detalheErro.setTitle("Tempo de Expiração de Empréstimos inválida.");
		}
		
		if (exception instanceof TempoDuracaoEmprestimoInvalidaException) {
		    detalheErro = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		    detalheErro.setDetail(exception.getMessage());
		    detalheErro.setTitle("Tempo de Duração de Empréstimos inválida.");
		}
		
		return detalheErro;
	}
}
