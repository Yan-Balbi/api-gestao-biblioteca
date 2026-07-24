package edu.yan.gestaobiblioteca.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import edu.yan.gestaobiblioteca.dto.autor.AutorPseudonimoResponseDto;
import edu.yan.gestaobiblioteca.dto.autor.AutorResumoResponseDto;
import edu.yan.gestaobiblioteca.dto.autor.AutorVerdadeiroResponseDto;
import edu.yan.gestaobiblioteca.model.Autor;

@Component
public class AutorMapper {
    private String montarNomeCompleto(Autor autor) {
        List<String> partes = new ArrayList<>();
        if (autor.getPrenome() != null && !autor.getPrenome().isBlank()) {
            partes.add(autor.getPrenome());
        }
        if (autor.getSobrenome() != null && !autor.getSobrenome().isBlank()) {
            partes.add(autor.getSobrenome());
        }
        if (autor.getSufixo() != null && !autor.getSufixo().isBlank()) {
            partes.add(autor.getSufixo());
        }
        return String.join(" ", partes);
    }
    
	private AutorResumoResponseDto toResumoResponseDto(Autor autor) {
        AutorResumoResponseDto dto = new AutorResumoResponseDto();
        dto.setId(autor.getId());
        dto.setPrenome(autor.getPrenome());
        dto.setSobrenome(autor.getSobrenome());
        dto.setSufixo(autor.getSufixo());
        dto.setNomeCompleto(montarNomeCompleto(autor));
        return dto;
    }

    public AutorVerdadeiroResponseDto toAutorVerdadeiroResponseDTO(Autor autor) {
    	AutorVerdadeiroResponseDto dto = new AutorVerdadeiroResponseDto();
    	
    	dto.setId(autor.getId());
    	dto.setPrenome(autor.getPrenome());
    	dto.setSobrenome(autor.getSobrenome());
    	dto.setSufixo(autor.getSufixo());
    	dto.setNomeCompleto(montarNomeCompleto(autor));
    	dto.setDataNascimento(autor.getDataNascimento());
    	dto.setDataMorte(autor.getDataMorte());
    	dto.setBiografia(autor.getBiografia());
    	dto.setAnonimo(autor.isAnonimo());
    	dto.setPseudonimos(autor.getPseudonimos().stream()
    			.map(this::toResumoResponseDto)
    			.collect(Collectors.toList()));
    	
    	return dto;
    }
    
    public AutorPseudonimoResponseDto toAutorPseudonimoResponseDTO(Autor autor) {
    	AutorPseudonimoResponseDto dto = new AutorPseudonimoResponseDto();
    	
    	dto.setId(autor.getId());
    	dto.setPrenome(autor.getPrenome());
    	dto.setSobrenome(autor.getSobrenome());
    	dto.setSufixo(autor.getSufixo());
    	dto.setNomeCompleto(montarNomeCompleto(autor));
    	dto.setDataNascimento(autor.getDataNascimento());
    	dto.setDataMorte(autor.getDataMorte());
    	dto.setBiografia(autor.getBiografia());
    	dto.setAnonimo(autor.isAnonimo());
    	if(autor.getAutorVerdadeiro() != null) dto.setAutorVerdadeiro(toAutorResumoResponseDTO(autor.getAutorVerdadeiro()));
    	
    	return dto;
    }
    
    private AutorResumoResponseDto toAutorResumoResponseDTO(Autor autorVerdadeiro) {
        AutorResumoResponseDto resumo = new AutorResumoResponseDto();
        resumo.setId(autorVerdadeiro.getId());
        resumo.setNomeCompleto(montarNomeCompleto(autorVerdadeiro));
        return resumo;
    }
}
