package edu.yan.gestaobiblioteca.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.yan.gestaobiblioteca.dto.regra.RegraUpdateDto;
import edu.yan.gestaobiblioteca.model.Regra;
import edu.yan.gestaobiblioteca.service.implementations.RegraServiceImplementation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("regra")
public class RegraController {
	RegraServiceImplementation regraServiceImplementation;
	
	public RegraController(RegraServiceImplementation regraServiceImplementation) {
		this.regraServiceImplementation = regraServiceImplementation;
	}
	
	@PutMapping("/atualizar/{regraId}")
	public ResponseEntity<Regra> atualizarRegra(@PathVariable Long regraId, @RequestBody @Valid RegraUpdateDto regraUpdateDto) {
		Regra regra = regraServiceImplementation.atualizar(regraId, regraUpdateDto);
		return ResponseEntity.ok(regra);
	}
}
