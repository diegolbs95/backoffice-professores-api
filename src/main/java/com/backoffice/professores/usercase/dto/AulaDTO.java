package com.backoffice.professores.usercase.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class AulaDTO {

    @Setter
    private Long id;
    private String titulo;
    private String descricao;
    private String dataPrevistaAula;
}
