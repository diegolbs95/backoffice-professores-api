package com.backoffice.professores.infra.factory;

import com.backoffice.professores.infra.persistencia.domain.Professor;
import com.backoffice.professores.usercase.dto.ProfessorDTO;
import lombok.experimental.UtilityClass;

import static com.backoffice.professores.infra.persistencia.enums.Role.PROFESSOR;
import static com.backoffice.professores.infra.persistencia.enums.StatusProfessor.AGUARDANDO_APROVACAO;

@UtilityClass
public class ProfessorFactory {

    public static Professor convertProfessor (ProfessorDTO dto){
        return Professor.builder()
                .email(dto.getEmail())
                .nome(dto.getNome())
                .role(dto.getRole() == null ? PROFESSOR : dto.getRole())
                .status(dto.getStatus() == null ? AGUARDANDO_APROVACAO : dto.getStatus())
                .build();
    }

    public static ProfessorDTO convertProfessorDTO (Professor professor){
        return ProfessorDTO.builder()
                .email(professor.getEmail())
                .id(professor.getId())
                .nome(professor.getNome())
                .role(professor.getRole())
                .senha(professor.getSenha())
                .status(professor.getStatus())
                .build();
    }
}
