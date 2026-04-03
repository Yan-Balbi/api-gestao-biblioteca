package edu.yan.gestaobiblioteca.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.yan.gestaobiblioteca.model.Regra;
import edu.yan.gestaobiblioteca.respository.RegraRepository;

@Configuration
public class InicializadorDadoRegra {	
	
	@Bean
	CommandLineRunner inicializarRegra(RegraRepository regraRepositoy) {
		return args -> {
			boolean existeRegra = regraRepositoy.haRegraInserida();
			
			if(!existeRegra) {
				Regra regra = new Regra();
				regra.setDuracaoSuspensaoUsuario(14);
				regra.setQuantidadeMaximaEmprestimos(5);
				regra.setTempoDuracaoAgendamento(14);
				regra.setTempoExpiracaoAgendamento(3);
				
				regraRepositoy.save(regra);
			}
		};
	}
}
