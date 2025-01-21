package com.abuhanaan.spring_auth.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.abuhanaan.spring_auth.dtos.request.SignInRequest;
import com.abuhanaan.spring_auth.dtos.request.SignUpRequest;
import com.abuhanaan.spring_auth.dtos.response.JwtAuthenticationResponse;
import com.abuhanaan.spring_auth.exceptions.AuthenticationException;
import com.abuhanaan.spring_auth.exceptions.BadRequestException;
import com.abuhanaan.spring_auth.models.Role;
import com.abuhanaan.spring_auth.models.User;
import com.abuhanaan.spring_auth.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        user = userService.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public JwtAuthenticationResponse signin(SignInRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthenticationException(String.format("User with email %s does not exist",
                        request.getEmail())));

        if (!(passwordEncoder.matches(request.getPassword(), user.getPassword()))) {
            System.out.println("Throwing Password Exception");
            throw new BadRequestException("Invalid Password");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
