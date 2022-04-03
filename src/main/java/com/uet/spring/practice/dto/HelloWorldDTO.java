package com.uet.spring.practice.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Data;

@Data
@JsonFilter(value = "HelloWorldFilter")
public class HelloWorldDTO {
    private final String author = "Hung_Pham";
    private String message;
    private int ver = 1;

    public HelloWorldDTO(String message) {
        this.message = message;
    }
}
