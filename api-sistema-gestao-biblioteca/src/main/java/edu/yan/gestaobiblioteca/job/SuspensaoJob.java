package edu.yan.gestaobiblioteca.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.yan.gestaobiblioteca.service.implementations.SuspensaoUsuarioServiceImplementation;

@Component
public class SuspensaoJob {
	
	SuspensaoUsuarioServiceImplementation suspensaoService;
	
	public SuspensaoJob(SuspensaoUsuarioServiceImplementation suspensaoService) {
		this.suspensaoService = suspensaoService;
	}
	
    @Scheduled(fixedDelay= 120000) //2min
    public void executar() {
    	System.out.println("executando batch");
    	suspensaoService.suspenderUsarios();
    }
}
