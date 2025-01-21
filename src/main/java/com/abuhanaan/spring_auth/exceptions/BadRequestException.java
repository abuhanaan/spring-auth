package com.abuhanaan.spring_auth.exceptions;

public class BadRequestException extends ApiException {

    public BadRequestException(String message) {
        super(message);
    }
}
