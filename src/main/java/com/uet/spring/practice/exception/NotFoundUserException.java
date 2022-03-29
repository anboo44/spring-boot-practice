package com.uet.spring.practice.exception;

public class NotFoundUserException extends BaseException {
    public NotFoundUserException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
