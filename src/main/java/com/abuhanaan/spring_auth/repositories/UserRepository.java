package com.abuhanaan.spring_auth.repositories;

import com.abuhanaan.spring_auth.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);
}
