package com.backoffice.professores.infra.factory;

import com.backoffice.professores.infra.persistencia.domain.Aula;
import com.backoffice.professores.usercase.dto.AulaDTO;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class AulaFactory {

    public static Aula aulaConvert(AulaDTO aulaDTO) {
        return Aula.builder()
                .dataPrevistaAula(LocalDate.parse(aulaDTO.getDataPrevistaAula()))
                .descricao(aulaDTO.getDescricao())
                .titulo(aulaDTO.getTitulo())
                .build();
    }

    public static AulaDTO aulaDTOConvert(Aula aula){
        return AulaDTO.builder()
                .id(aula.getId())
                .dataPrevistaAula(String.valueOf(aula.getDataPrevistaAula()))
                .descricao(aula.getDescricao())
                .titulo(aula.getTitulo())
                .build();
    }
}
