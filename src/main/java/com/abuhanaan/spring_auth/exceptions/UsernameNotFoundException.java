package com.abuhanaan.spring_auth.exceptions;

public class UsernameNotFoundException extends ApiException {

    public UsernameNotFoundException(String message) {
        super(message);
    }
}
