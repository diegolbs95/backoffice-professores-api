package com.backoffice.professores.usercase.service.impl;

import com.backoffice.professores.infra.config.security.JwtService;
import com.backoffice.professores.infra.exception.UserNotFoundException;
import com.backoffice.professores.infra.exception.UserUnauthorizedException;
import com.backoffice.professores.infra.persistencia.domain.Professor;
import com.backoffice.professores.infra.persistencia.domain.TokenEntity;
import com.backoffice.professores.infra.persistencia.repository.ProfessorRepository;
import com.backoffice.professores.infra.persistencia.repository.TokenRepository;
import com.backoffice.professores.usercase.dto.AuthenticationRequest;
import com.backoffice.professores.usercase.service.ProfessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.List;
import java.util.Optional;

import static com.backoffice.professores.infra.persistencia.enums.StatusProfessor.AGUARDANDO_APROVACAO;
import static com.backoffice.professores.infra.persistencia.enums.StatusProfessor.APROVADO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    ProfessorRepository professorRepository;
    @Mock
    ProfessorService professorService;
    @Mock
    JwtService jwtService;
    @Mock
    TokenRepository tokenRepository;

    @InjectMocks
    AuthServiceImpl authService;

    Professor professor;
    AuthenticationRequest authenticationRequest;

    @BeforeEach
    void setUp() {

        professor = Professor.builder()
                .status(APROVADO)
                .build();

        authenticationRequest = AuthenticationRequest.builder()
                .email("test@backoffice.com")
                .password("password")
                .build();
    }

    @Test
    void testAuthenticateValidandoCredenciais() {

        when(professorRepository.findByEmail(anyString())).thenReturn(Optional.of(professor));
        when(jwtService.generateToken(any())).thenReturn("testToken");
        when(jwtService.generateRefreshToken(any())).thenReturn("testRefreshToken");

        assertNotNull(authService.authenticate(authenticationRequest));
        verify(professorService, times(1)).salvarTokenProfessor(any(), anyString());
    }

    @Test
    void testAuthenticateProfessorNaoEncontrado() {

        assertThrows(UserNotFoundException.class, () -> {
            authService.authenticate(authenticationRequest);
        });
        verify(professorService, never()).salvarTokenProfessor(any(), anyString());
    }

    @Test
    void testAuthenticateProfessorStatusAguardandoAprovacao() {
        professor.setStatus(AGUARDANDO_APROVACAO);

        when(professorRepository.findByEmail(anyString())).thenReturn(Optional.of(professor));

        assertThrows(UserUnauthorizedException.class, () -> {
            authService.authenticate(authenticationRequest);
        });

        verify(professorService, never()).salvarTokenProfessor(any(), anyString());
    }

    @Test
    void revokeAllProfessorTokens() {

        var token1 = TokenEntity.builder()
                .expired(false)
                .revoked(false)
                .build();
        var token2 = TokenEntity.builder()
                .expired(false)
                .revoked(false)
                .build();

        when(tokenRepository.findAllByProfessorIdAndExpiredIsFalseAndRevokedIsFalse(any()))
                .thenReturn(List.of(token1, token2));

        authService.revokeAllProfessorTokens(professor);

        verify(tokenRepository, times(1)).saveAll(anyList());
    }
}