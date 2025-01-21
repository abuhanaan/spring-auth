package com.abuhanaan.spring_auth.controllers;

// import com.google.gson.Gson;
// import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.abuhanaan.spring_auth.dtos.response.ErrorResponse;
import com.abuhanaan.spring_auth.exceptions.AuthenticationException;
import com.abuhanaan.spring_auth.exceptions.AuthorizationException;
import com.abuhanaan.spring_auth.exceptions.BadRequestException;
import com.abuhanaan.spring_auth.exceptions.ConflictException;
import com.abuhanaan.spring_auth.exceptions.NotFoundException;
import com.abuhanaan.spring_auth.exceptions.ProcessingException;
import com.abuhanaan.spring_auth.exceptions.UsernameNotFoundException;
import com.abuhanaan.spring_auth.models.ErrorCode;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandler {

    // private final Gson gson;
    private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    // public ErrorHandler() {
    // this.gson = new GsonBuilder().setPrettyPrinting().create();
    // }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(HttpServletRequest request, BadRequestException e) {
        ErrorResponse response = ErrorResponse.builder()
                .status(false)
                .error(ErrorCode.INPUT)
                .message(e.getMessage())
                .build();
        logError(request, response);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthentication(HttpServletRequest request, Exception e) {
        ErrorResponse response = ErrorResponse.builder()
                .status(false)
                .error(ErrorCode.AUTHENTICATION)
                .message(e.getMessage()).build();
        logError(request, response);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ErrorResponse> handleAuthorization(HttpServletRequest request, Exception e) {
        ErrorResponse response = ErrorResponse.builder()
                .status(false)
                .error(ErrorCode.UNAUTHORIZED)
                .message(e.getMessage()).build();
        logError(request, response);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflict(HttpServletRequest request, Exception e) {
        ErrorResponse response = ErrorResponse.builder()
                .status(false)
                .error(ErrorCode.CONFLICT)
                .message(e.getMessage()).build();
        logError(request, response);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(HttpServletRequest request, Exception e) {
        ErrorResponse response = ErrorResponse.builder()
                .status(false)
                .error(ErrorCode.NOT_FOUND)
                .message(e.getMessage()).build();
        logError(request, response);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFound(HttpServletRequest request, Exception e) {
        ErrorResponse response = ErrorResponse.builder()
                .status(false)
                .error(ErrorCode.NOT_FOUND)
                .message(e.getMessage()).build();
        logError(request, response);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProcessingException.class)
    public ResponseEntity<ErrorResponse> handleProcessing(HttpServletRequest request, Exception e) {
        ErrorResponse response = ErrorResponse.builder()
                .status(false)
                .error(ErrorCode.PROCESSING)
                .message(e.getMessage()).build();
        logError(request, response);
        return new ResponseEntity<>(response, HttpStatus.PROCESSING);
    }

    private void logError(HttpServletRequest request, ErrorResponse response) {
        logger.info("Error sending " + request.getMethod() + " request to " + request.getRequestURL());
        // logger.error("Error Response: " + gson.toJson(response));
        logger.error("Error Response: " + response);
    }
}
