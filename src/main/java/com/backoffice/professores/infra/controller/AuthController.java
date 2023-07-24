package com.backoffice.professores.infra.controller;

import com.backoffice.professores.usercase.dto.AuthenticationRequest;
import com.backoffice.professores.usercase.dto.AuthenticationResponse;

public interface AuthController {

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
