package com.backoffice.professores.usercase.service.impl;

import com.backoffice.professores.infra.exception.UserNotFoundException;
import com.backoffice.professores.infra.factory.AulaFactory;
import com.backoffice.professores.infra.persistencia.repository.AulaRepository;
import com.backoffice.professores.usercase.dto.AulaDTO;
import com.backoffice.professores.usercase.service.AulaService;
import com.backoffice.professores.usercase.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AulaServiceImpl implements AulaService {

    private final AulaRepository aulaRepository;
    private final ProfessorService professorService;

    @Override
    public AulaDTO registro(String professorToken, AulaDTO aulaDTO) {
        var professor = professorService.buscarProfessorNoToken(professorToken);

        var aula = AulaFactory.aulaConvert(aulaDTO);
        aula.setProfessor(professor);

        aulaRepository.save(aula);
        log.info("Aula cadastrada com sucesso");
        aulaDTO.setId(aula.getId());

        return aulaDTO;
    }

    @Override
    public List<AulaDTO> listar(String token) {

        log.info("Extraindo dados do token.");
        var professor = professorService.buscarProfessorNoToken(token);

        var listAulaDTO = new ArrayList<AulaDTO>();
        var listAulas = aulaRepository.findAllByProfessorId(professor.getId());
        listAulas.forEach(aula -> listAulaDTO.add(AulaFactory.aulaDTOConvert(aula)));

        return listAulaDTO;
    }

    @Override
    public void atualizar(String emailProfessor, String idAula, AulaDTO aulaDTO) {
        var professor = professorService.buscarProfessorNoToken(emailProfessor);
        var aula = aulaRepository.findById(idAula).orElseThrow(() ->
                        new UserNotFoundException("Aula não cadastrada na base de dados."));

        if (aula.getProfessor().getId().equals(professor.getId())){
            BeanUtils.copyProperties(AulaFactory.aulaConvert(aulaDTO), aula, "id", "professor");
            log.info(String.format("Auteracao de aula com id: %s, para professor: %s!", idAula,
                    professor.getNome()));
            aulaRepository.save(aula);
        }
    }
}
