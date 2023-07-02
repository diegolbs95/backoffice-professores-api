package com.backoffice.professores.usercase.service;

import com.backoffice.professores.infra.persistencia.domain.Professor;
import com.backoffice.professores.usercase.dto.ProfessorDTO;

public interface ProfessorService {

    ProfessorDTO registro(ProfessorDTO professorDTO);

    Professor buscarProfessorNoToken(String token);
    public void salvarTokenProfessor(Professor professor, String jwtToken);
}
