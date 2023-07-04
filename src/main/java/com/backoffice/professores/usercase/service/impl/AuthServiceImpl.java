package com.backoffice.professores.usercase.service.impl;

import com.backoffice.professores.infra.config.security.JwtService;
import com.backoffice.professores.infra.persistencia.domain.Professor;
import com.backoffice.professores.infra.persistencia.repository.ProfessorRepository;
import com.backoffice.professores.infra.persistencia.repository.TokenRepository;
import com.backoffice.professores.usercase.dto.AuthenticationRequest;
import com.backoffice.professores.usercase.dto.AuthenticationResponse;
import com.backoffice.professores.usercase.service.AuthService;
import com.backoffice.professores.usercase.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.backoffice.professores.infra.persistencia.enums.StatusProfessor.AGUARDANDO_APROVACAO;
import static com.backoffice.professores.infra.persistencia.enums.StatusProfessor.PEDENTE;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final ProfessorRepository professorRepository;
    private final ProfessorService professorService;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var professor = professorRepository.findByEmail(request.getEmail()).orElseThrow(()->
                new UsernameNotFoundException("Professor não encontrado."));

        if (professor.getStatus().equals(AGUARDANDO_APROVACAO) || professor.getStatus().equals(PEDENTE)) {
            log.info("Status cadastro professor Aguardando aprovacao ou pendente. Verifique com AdmBackoffice");
            return null;
        }

        var jwtToken = jwtService.generateToken(professor);
        var refreshToken = jwtService.generateRefreshToken(professor);
        revokeAllProfessorTokens(professor);
        professorService.salvarTokenProfessor(professor, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void revokeAllProfessorTokens(Professor professor) {
        var listTokens = tokenRepository.findAllByProfessorIdAndExpiredIsFalseAndRevokedIsFalse(professor.getId());
        if (listTokens.isEmpty())
            return;
        listTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(listTokens);
    }
}
