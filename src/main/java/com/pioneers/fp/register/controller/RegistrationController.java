package com.pioneers.fp.register.controller;

import com.pioneers.fp.register.dto.RegisterRequest;
import com.pioneers.fp.register.dto.RegisterResponse;
import com.pioneers.fp.register.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        return registrationService.register(request);
    }
}
