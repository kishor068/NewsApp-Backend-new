package com.example.Dlithe.repository;

import com.example.Dlithe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    // Optional additions
    Optional<User> findByUsername(String username);
}
