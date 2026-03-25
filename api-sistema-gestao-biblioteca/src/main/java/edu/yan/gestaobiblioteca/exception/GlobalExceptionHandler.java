package edu.yan.gestaobiblioteca.exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({
	    CpfInvalidoException.class,
	    CpfJaCadastradoException.class,
	    UsuarioNaoEncontrado.class,
	    EmailJaCadastradoException.class
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
		
		return detalheErro;
    }
}
