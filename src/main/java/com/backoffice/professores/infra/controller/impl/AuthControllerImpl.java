package com.backoffice.professores.infra.controller.impl;

import com.backoffice.professores.infra.controller.AuthController;
import com.backoffice.professores.usercase.dto.AuthenticationRequest;
import com.backoffice.professores.usercase.dto.AuthenticationResponse;
import com.backoffice.professores.usercase.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService service;

    @Override
    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) {
        return service.authenticate(request);
    }
}
