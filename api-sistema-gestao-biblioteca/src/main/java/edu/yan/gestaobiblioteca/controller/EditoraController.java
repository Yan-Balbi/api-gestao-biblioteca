package edu.yan.gestaobiblioteca.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.yan.gestaobiblioteca.dto.editora.EditoraInsertDto;
import edu.yan.gestaobiblioteca.dto.editora.EditoraUpdateDto;
import edu.yan.gestaobiblioteca.model.Editora;
import edu.yan.gestaobiblioteca.service.implementations.EditoraServiceImplementation;

@RestController
@RequestMapping("editora")
public class EditoraController {
	EditoraServiceImplementation editoraService;
	
	public EditoraController(EditoraServiceImplementation editoraService) {
		this.editoraService = editoraService;
	}
	
	@PostMapping("/inserir")
	public ResponseEntity<Editora> inserir(@RequestBody EditoraInsertDto editoraInsertDto) {
		Editora editora = editoraService.inserir(editoraInsertDto);
		URI local = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(editora.getId()).toUri();
		return ResponseEntity.created(local).body(editora);
	}
	
	@PutMapping("/{id}/atualizar")
	public ResponseEntity<Editora> atualizar(@PathVariable Long id, EditoraUpdateDto editoraUpdateDto) {
		Editora editora = editoraService.atualizar(id, editoraUpdateDto);
		return ResponseEntity.ok(editora);
	}
	
	@DeleteMapping("/{id}/inativar")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		editoraService.inativar(id);	
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{id}/reativar")
	public ResponseEntity<Void> reativar(@PathVariable Long id) {
	    editoraService.reativar(id);
	    return ResponseEntity.noContent().build();
	}
	
}
