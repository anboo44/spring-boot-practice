package com.uet.spring.practice.controller;

import com.uet.spring.practice.dto.ErrorResDTO;
import com.uet.spring.practice.exception.NotFoundUserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<?> handleNotFound(Exception ex, WebRequest request) {
        var dto = new ErrorResDTO(404, ex.getMessage());
        return ResponseEntity.status(404).body(dto);
    }

    @ExceptionHandler({ ConstraintViolationException.class, IllegalArgumentException.class })
    public ResponseEntity<?> handleBadRequest(Exception ex, WebRequest request) {
        var dto = new ErrorResDTO(400, "Bad Request");
        return ResponseEntity.status(400).body(dto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleInvalidRequest(Exception ex, WebRequest request) {
        var dto = new ErrorResDTO(400, ex.getMessage());
        return ResponseEntity.status(400).body(dto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception ex, WebRequest request) {
        ex.printStackTrace();

        var dto = new ErrorResDTO(500, "Unknown error happened");
        return ResponseEntity.status(500).body(dto);
    }
}
