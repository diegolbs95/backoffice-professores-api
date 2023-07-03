package com.backoffice.professores.infra.persistencia.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_aulas")
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String titulo;
    @Setter
    private String descricao;
    @Setter
    private LocalDate dataPrevistaAula;

    @ManyToOne
    @JsonIgnore
    @Setter
    private Professor professor;
}
