package com.backoffice.professores.usercase.service;

import com.backoffice.professores.infra.persistencia.domain.Professor;
import com.backoffice.professores.usercase.dto.AuthenticationRequest;
import com.backoffice.professores.usercase.dto.AuthenticationResponse;

public interface AuthService {

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void revokeAllProfessorTokens(Professor professor);
}
