package com.backoffice.professores.usercase.service.impl;


import com.backoffice.professores.infra.config.security.JwtService;
import com.backoffice.professores.infra.exception.UserNotFoundException;
import com.backoffice.professores.infra.factory.ProfessorFactory;
import com.backoffice.professores.infra.persistencia.domain.Professor;
import com.backoffice.professores.infra.persistencia.domain.TokenEntity;
import com.backoffice.professores.infra.persistencia.enums.TokenType;
import com.backoffice.professores.infra.persistencia.repository.ProfessorRepository;
import com.backoffice.professores.infra.persistencia.repository.TokenRepository;
import com.backoffice.professores.usercase.dto.ProfessorDTO;
import com.backoffice.professores.usercase.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ProfessorDTO registro(ProfessorDTO professorDTO) {

        var professor = ProfessorFactory.convertProfessor(professorDTO);
        log.info("Api registro de professor.");
        professor.setSenha(passwordEncoder.encode(professorDTO.getSenha()));
        var professorSalvo = professorRepository.save(professor);
        log.info("Professor salvo com sucesso na base de dados.");

        return ProfessorFactory.convertProfessorDTO(professorSalvo);
    }

    @Override
    public Professor buscarProfessorNoToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            var jwtToken = token.substring(7);
            var emailProfessor = jwtService.extractUsername(jwtToken);
            return professorRepository.findByEmail(emailProfessor)
                    .orElseThrow(() -> new UserNotFoundException("Não existe professor cadastrado com email: "
                            + emailProfessor));
        }
        return null;
    }

    @Override
    public void salvarTokenProfessor(Professor professor, String jwtToken) {
        var token = TokenEntity.builder()
                .professor(professor)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}
