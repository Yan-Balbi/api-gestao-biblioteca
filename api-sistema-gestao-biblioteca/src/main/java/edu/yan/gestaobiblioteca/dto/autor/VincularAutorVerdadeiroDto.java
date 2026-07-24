package edu.yan.gestaobiblioteca.dto.autor;

import jakarta.validation.constraints.NotNull;

public class VincularAutorVerdadeiroDto {
    @NotNull(message = "Campo 'autorVerdadeiroId' é obrigatório")
    private Long autorVerdadeiroId;

    public Long getAutorVerdadeiroId() {
        return autorVerdadeiroId;
    }
    public void setAutorVerdadeiroId(Long autorVerdadeiroId) {
        this.autorVerdadeiroId = autorVerdadeiroId;
    }
}
