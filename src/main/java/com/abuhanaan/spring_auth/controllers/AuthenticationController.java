package com.abuhanaan.spring_auth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import com.abuhanaan.spring_auth.dtos.request.SignInRequest;
import com.abuhanaan.spring_auth.dtos.request.SignUpRequest;
import com.abuhanaan.spring_auth.dtos.response.ApiResponse;
import com.abuhanaan.spring_auth.dtos.response.SuccessResponse;
import com.abuhanaan.spring_auth.services.AuthenticationService;
import com.abuhanaan.spring_auth.utils.InputValidator;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    // public JwtAuthenticationResponse signup(@RequestBody SignUpRequest request) {
    public ApiResponse signup(@Valid @RequestBody SignUpRequest request, BindingResult bindingResult) {
        InputValidator.validate(bindingResult, request.getEmail());
        return authenticationService.signup(request);
    }

    @PostMapping("/signin")
    // public JwtAuthenticationResponse signin(@RequestBody SignInRequest request) {
    public ResponseEntity<ApiResponse> signin(@Valid @RequestBody SignInRequest request, BindingResult bindingResult) {
        InputValidator.validate(bindingResult, request.getEmail());
        SuccessResponse response = SuccessResponse.buildSuccessResponse(authenticationService.signin(request));
        return ResponseEntity.ok(response);
    }
}
