package edu.yan.gestaobiblioteca.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.yan.gestaobiblioteca.dto.autor.AutorPseudonimoInsertDto;
import edu.yan.gestaobiblioteca.dto.autor.AutorPseudonimoResponseDto;
import edu.yan.gestaobiblioteca.dto.autor.AutorPseudonimoUpdateDto;
import edu.yan.gestaobiblioteca.dto.autor.AutorVerdadeiroInsertDto;
import edu.yan.gestaobiblioteca.dto.autor.AutorVerdadeiroResponseDto;
import edu.yan.gestaobiblioteca.dto.autor.AutorVerdadeiroUpdateDto;
import edu.yan.gestaobiblioteca.mappers.AutorMapper;
import edu.yan.gestaobiblioteca.model.Autor;
import edu.yan.gestaobiblioteca.service.implementations.AutorServiceImplementation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("autor")
public class AutorController {
	AutorServiceImplementation autorService;
	
	public AutorController(AutorServiceImplementation autorService) {
		this.autorService = autorService;
	}	
	
	@PostMapping("/verdadeiro")
	public ResponseEntity<AutorVerdadeiroResponseDto> inserirAutorVerdadeiro(@RequestBody @Valid AutorVerdadeiroInsertDto autorVerdadeiroDto){
		Autor autor = autorService.inserirAutorVerdadeiro(autorVerdadeiroDto);
		URI local = ServletUriComponentsBuilder.fromCurrentRequest().path("/autor/verdadeiro/{id}").buildAndExpand(autor.getId()).toUri();
		AutorMapper autorMapper = new AutorMapper();
		return ResponseEntity.created(local).body(autorMapper.toAutorVerdadeiroResponseDTO(autor));
	}
	
	@PostMapping("/verdadeiro/{autorVerdadeiroid}/pseudonimo")
	public ResponseEntity<AutorPseudonimoResponseDto> inserirAutorPseudonimoComVinculo(@PathVariable Long autorVerdadeiroid, @RequestBody @Valid AutorPseudonimoInsertDto autorPseudonimoDto){
		Autor autor = autorService.inserirAutorPseudonimo(autorVerdadeiroid, autorPseudonimoDto);
		URI local = ServletUriComponentsBuilder.fromCurrentRequest().path("/autor/pseudonimo/{pseudonimoId}").buildAndExpand(autor.getId()).toUri();
		AutorMapper autorMapper = new AutorMapper();
		return ResponseEntity.created(local).body(autorMapper.toAutorPseudonimoResponseDTO(autor));
	}
	
	@PostMapping("/pseudonimo")
	public ResponseEntity<AutorPseudonimoResponseDto> inserirAutorPseudonimoSemVinculo(@RequestBody @Valid AutorPseudonimoInsertDto autorPseudonimoDto){
		Autor autor = autorService.inserirAutorPseudonimo(autorPseudonimoDto);
		URI local = ServletUriComponentsBuilder.fromCurrentRequest().path("/autor/pseudonimo/{id}").buildAndExpand(autor.getId()).toUri();
		AutorMapper autorMapper = new AutorMapper(); 
		return ResponseEntity.created(local).body(autorMapper.toAutorPseudonimoResponseDTO(autor));
	}
	
	@PutMapping("/verdadeiro/{id}")
	public ResponseEntity<AutorVerdadeiroResponseDto> atualizarAutorVerdadeiro(@PathVariable Long id, @Valid @RequestBody AutorVerdadeiroUpdateDto dto) {
	    Autor autor = autorService.atualizarAutorVerdadeiro(id, dto);
	    AutorMapper autorMapper = new AutorMapper(); 
	    return ResponseEntity.ok(autorMapper.toAutorVerdadeiroResponseDTO(autor));
	}
	
	@PutMapping("/pseudonimo/{id}")
	public ResponseEntity<AutorPseudonimoResponseDto> atualizarAutorPseudonimo(@PathVariable Long id, @Valid @RequestBody AutorPseudonimoUpdateDto dto) {
	    Autor autor = autorService.atualizarAutorPseudonimo(id, dto);
	    AutorMapper autorMapper = new AutorMapper(); 
	    return ResponseEntity.ok(autorMapper.toAutorPseudonimoResponseDTO(autor));
	}
	
	@PutMapping("/pseudonimo/{pseudonimoId}/vinculo/{autorVerdadeiroId}")
	public ResponseEntity<AutorPseudonimoResponseDto> vincularAutorVerdadeiroAUmPseudonimo(@PathVariable Long pseudonimoId, @PathVariable Long autorVerdadeiroId){
		Autor autor = autorService.vincularAutorVerdadeiro(pseudonimoId, autorVerdadeiroId);
		AutorMapper autorMapper = new AutorMapper(); 
		return ResponseEntity.ok(autorMapper.toAutorPseudonimoResponseDTO(autor));
	}
	
	@PutMapping("/pseudonimo/{pseudonimoId}/vinculo")
	public ResponseEntity<AutorPseudonimoResponseDto> desvincularPseudonimoDeUmAutorVerdadeiro(@PathVariable Long pseudonimoId){
		Autor autor = autorService.desvincularAutorVerdadeiro(pseudonimoId);
		AutorMapper autorMapper = new AutorMapper(); 
		return ResponseEntity.ok(autorMapper.toAutorPseudonimoResponseDTO(autor));
	}
}
