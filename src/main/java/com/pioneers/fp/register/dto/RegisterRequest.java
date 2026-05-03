package com.pioneers.fp.register.dto;

import lombok.Builder;

@Builder
public record RegisterRequest(
        String name,
        int age
) {
}
