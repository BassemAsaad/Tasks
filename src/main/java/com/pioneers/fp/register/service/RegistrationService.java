package com.pioneers.fp.register.service;

import com.pioneers.fp.exceptions.RegisterException;
import com.pioneers.fp.register.dto.RegisterRequest;
import com.pioneers.fp.register.dto.RegisterResponse;
import com.pioneers.fp.register.model.Student;
import com.pioneers.fp.register.service.strategy.RegistrationEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {
    private final RegistrationEngine registrationEngine;

    public RegisterResponse register(final RegisterRequest request) {
        final Student student = new  Student();
        registrationEngine.register(
                request,
                () -> {
                    student.setName(request.name());
                    student.setAge(request.age());
                    log.info("Student {} is registered", student.getName());
                },
                message -> {
                    log.error(message);
                    throw new RegisterException("Registration failed: "+ message);
                });

        return RegisterResponse.from(student);
    }
}
