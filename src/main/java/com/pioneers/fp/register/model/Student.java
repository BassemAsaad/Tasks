package com.pioneers.fp.register.model;

import lombok.*;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private UUID id = UUID.randomUUID();
    private String name;
    private int age;

}
