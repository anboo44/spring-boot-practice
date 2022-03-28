package com.uet.spring.practice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloWorldDTO {
    private final String author = "Hung_Pham";
    private String message;

    public HelloWorldDTO(String message) {
        this.message = message;
    }
}
