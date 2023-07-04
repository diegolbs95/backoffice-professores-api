package com.backoffice.professores.infra.persistencia.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tb_aulas")
public class Aula {

    @Id
    private String id;

    @Setter
    private String titulo;
    @Setter
    private String descricao;
    @Setter
    private LocalDate dataPrevistaAula;

    @DBRef
    @Setter
    private Professor professor;
}
