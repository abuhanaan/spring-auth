package com.abuhanaan.spring_auth.utils;

import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;

import com.abuhanaan.spring_auth.exceptions.BadRequestException;

public class InputValidator {

    public static void validate(BindingResult bindingResult, String requestId)
            throws BadRequestException {
        if (!bindingResult.hasErrors()) {
            return;
        }

        String errorMessage = bindingResult.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(" // "));

        throw new BadRequestException(errorMessage);
    }
}
