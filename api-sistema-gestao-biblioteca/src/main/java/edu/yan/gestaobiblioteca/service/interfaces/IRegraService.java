package edu.yan.gestaobiblioteca.service.interfaces;

import edu.yan.gestaobiblioteca.dto.regra.RegraUpdateDto;
import edu.yan.gestaobiblioteca.model.Regra;

public interface IRegraService {
	
	Regra inserir(Regra regra);
	
	Regra atualizar(Long id, RegraUpdateDto regraUpdateDto);
}
