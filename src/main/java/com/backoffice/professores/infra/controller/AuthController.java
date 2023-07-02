package com.backoffice.professores.infra.controller;

import com.backoffice.professores.usercase.dto.AuthenticationRequest;
import com.backoffice.professores.usercase.dto.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

public interface AuthController {

    ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request);
}
