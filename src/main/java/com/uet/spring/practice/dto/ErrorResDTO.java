package com.uet.spring.practice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorResDTO {
    private String timestamp;
    private int status;
    private String message;

    public ErrorResDTO(int status, String message) {
        this.status    = status;
        this.message   = message;
        this.timestamp = new Date().toString();
    }
}
