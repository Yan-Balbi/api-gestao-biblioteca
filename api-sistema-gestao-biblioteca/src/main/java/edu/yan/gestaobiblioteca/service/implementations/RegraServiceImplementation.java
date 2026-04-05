package edu.yan.gestaobiblioteca.service.implementations;

import org.springframework.stereotype.Service;

import edu.yan.gestaobiblioteca.dto.regra.RegraUpdateDto;
import edu.yan.gestaobiblioteca.exception.regra.DuracaoSuspensaoDeUsuarioInvalidaException;
import edu.yan.gestaobiblioteca.exception.regra.QuantidadeMaximaEmprestimosInvalidaException;
import edu.yan.gestaobiblioteca.exception.regra.RegraJaInseridaException;
import edu.yan.gestaobiblioteca.exception.regra.RegraNaoEncontradaException;
import edu.yan.gestaobiblioteca.exception.regra.TempoDeExpiracaoReservaInvalidaException;
import edu.yan.gestaobiblioteca.exception.regra.TempoDuracaoEmprestimoInvalidaException;
import edu.yan.gestaobiblioteca.model.Regra;
import edu.yan.gestaobiblioteca.respository.RegraRepository;
import edu.yan.gestaobiblioteca.service.interfaces.IRegraService;
import jakarta.transaction.Transactional;

@Service
public class RegraServiceImplementation implements IRegraService{
	private final RegraRepository regraRepository;
	
	public RegraServiceImplementation(RegraRepository regraRepository) {
		this.regraRepository = regraRepository;
	}
		
	@Override
	public Regra inserir(Regra regra) {
		
		if(regraRepository.haRegraInserida()) {
			throw new RegraJaInseridaException("Já há uma regra inserida no Banco de Dados.");
		}
		if(regra.getDuracaoSuspensaoUsuario() <= 0) {
			throw new DuracaoSuspensaoDeUsuarioInvalidaException("Valor da duração de suspensão inválido.");
		}
		if(regra.getTempoDuracaoEmprestimo() <= 0 ) {
			throw new TempoDuracaoEmprestimoInvalidaException("Valor de tempo de duração do empréstimo inválido.");
		}
		if(regra.getTempoExpiracaoReserva() <= 0) {
			throw new TempoDeExpiracaoReservaInvalidaException("Valor do tempo de expiração inválido.");
		}
		if(regra.getQuantidadeMaximaEmprestimos() <= 0) {
			throw new QuantidadeMaximaEmprestimosInvalidaException("Valor de quantidade max de emprétismos inválido.");
		}
		
		return regraRepository.save(regra);
	}

	@Override
	@Transactional
	public Regra atualizar(Long id, RegraUpdateDto regraUpdateDto) {
		System.out.println("DuracaoSuspensaoDeUsuarioInvalidaException - "+regraUpdateDto.getDuracaoSuspensaoUsuario());
		if(regraRepository.findById(id).isEmpty()) {
			throw new RegraNaoEncontradaException("Regra não encontrada");
		}
		if(regraUpdateDto.getDuracaoSuspensaoUsuario() <= 0) {
			throw new DuracaoSuspensaoDeUsuarioInvalidaException("Valor '"+regraUpdateDto.getDuracaoSuspensaoUsuario()+"' da duração de suspensão inválido.");
		}
		if(regraUpdateDto.getTempoDuracaoEmprestimo() <= 0 ) {
			throw new TempoDuracaoEmprestimoInvalidaException("Valor '"+regraUpdateDto.getTempoDuracaoEmprestimo()+"' de tempo de duração do empréstimo inválido.");
		}
		if(regraUpdateDto.getTempoExpiracaoReserva() <= 0) {
			throw new TempoDeExpiracaoReservaInvalidaException("Valor '"+regraUpdateDto.getTempoExpiracaoReserva()+"' do tempo de expiração de reservas inválido.");
		}
		if(regraUpdateDto.getQuantidadeMaximaEmprestimos() <= 0) {
			throw new QuantidadeMaximaEmprestimosInvalidaException("Valor '"+regraUpdateDto.getQuantidadeMaximaEmprestimos()+"' de quantidade max de emprétismos inválido.");
		}
		
		Regra regraBd = regraRepository.findRegraById(id).orElseThrow(() -> new RuntimeException());
		regraBd.setQuantidadeMaximaEmprestimos(regraUpdateDto.getQuantidadeMaximaEmprestimos());
		regraBd.setDuracaoSuspensaoUsuario(regraUpdateDto.getDuracaoSuspensaoUsuario());
		regraBd.setTempoDuracaoEmprestimo(regraUpdateDto.getTempoDuracaoEmprestimo());
		regraBd.setTempoExpiracaoReserva(regraUpdateDto.getTempoExpiracaoReserva());
		return regraBd;
	}
}
