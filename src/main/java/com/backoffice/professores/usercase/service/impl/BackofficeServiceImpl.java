package com.backoffice.professores.usercase.service.impl;

import com.backoffice.professores.infra.exception.UserNotFoundException;
import com.backoffice.professores.infra.persistencia.enums.Role;
import com.backoffice.professores.infra.persistencia.enums.StatusProfessor;
import com.backoffice.professores.infra.persistencia.repository.ProfessorRepository;
import com.backoffice.professores.usercase.service.BackofficeService;
import com.backoffice.professores.usercase.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BackofficeServiceImpl implements BackofficeService {

    private final ProfessorRepository professorRepository;
    private final ProfessorService professorService;

    @Override
    public String aprovarRegistroProfessor(String token, String emailProfessor) {
        log.info("Solicitacao de aprovacao cadastro do email: " + emailProfessor);
        var professorAdmin = professorService.buscarProfessorNoToken(token);

        if (!professorAdmin.getRole().equals(Role.ADMIN)){
            return "USUARIO_NAO_TEM_PERMISSAO_PARA_APROVAR_CADASTRO";
        }

        log.info("Admin altorizado aprovar cadastro.");
        var professor = professorRepository.findByEmail(emailProfessor).orElseThrow(() ->
                new UserNotFoundException("Professor com email solicitado não existe cadastro."));
        professor.setStatus(StatusProfessor.APROVADO);
        professorRepository.save(professor);

        return "CADASTRO_APROVADO";
    }
}
