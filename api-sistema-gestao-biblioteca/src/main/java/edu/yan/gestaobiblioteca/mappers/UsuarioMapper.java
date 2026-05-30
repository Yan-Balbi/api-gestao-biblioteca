package edu.yan.gestaobiblioteca.mappers;

import org.springframework.stereotype.Component;

import edu.yan.gestaobiblioteca.dto.usuario.UsuarioResponseDto;
import edu.yan.gestaobiblioteca.model.Usuario;

@Component
public class UsuarioMapper {

    public UsuarioResponseDto toResponseDTO(Usuario usuario) {
        UsuarioResponseDto dto = new UsuarioResponseDto();

        dto.setId(usuario.getId());
        dto.setCpf(usuario.getCpf());
        dto.setNomeUsuario(usuario.getNomeUsuario());
        dto.setEmail(usuario.getEmail());
        dto.setPapel(usuario.getPapel());
        dto.setCreatedAt(usuario.getCreatedAt());
        dto.setUpdatedAt(usuario.getUpdatedAt());

        return dto;
    }
}
