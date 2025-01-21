package com.abuhanaan.spring_auth.services;

import com.abuhanaan.spring_auth.models.User;
import com.abuhanaan.spring_auth.repositories.UserRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.abuhanaan.spring_auth.exceptions.BadRequestException;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.abuhanaan.spring_auth.exceptions.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserDetailsService userDetailsService() {
    return new UserDetailsService() {

      @Override
      public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username)
            .orElseThrow(() -> new BadRequestException(String.format("User with email %s not found", username)));
      }
    };
  }

  public User save(User newUser) {
    if (newUser.getId() == null) {
      newUser.setCreatedAt(LocalDateTime.now());
    }
    newUser.setUpdatedAt(LocalDateTime.now());
    return userRepository.save(newUser);
  }
}
