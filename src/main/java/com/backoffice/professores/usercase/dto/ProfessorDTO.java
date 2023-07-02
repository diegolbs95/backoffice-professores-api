package com.backoffice.professores.usercase.dto;

import com.backoffice.professores.infra.persistencia.enums.Role;
import com.backoffice.professores.infra.persistencia.enums.StatusProfessor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfessorDTO {

    private Long id;
    private String email;
    private String senha;
    private String nome;
    private StatusProfessor status;
    private Role role;
}
