package com.pioneers.fp.register.dto;

import com.pioneers.fp.register.model.Student;
import lombok.Builder;

@Builder
public record RegisterResponse(
        String name,
        int age
) {
    public static RegisterResponse from(Student student) {
        return RegisterResponse.builder()
                .name(student.getName())
                .age(student.getAge())
                .build();
    }
}
