package com.example.Dlithe.service;

import com.example.Dlithe.models.User;
import com.example.Dlithe.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

<<<<<<< HEAD
    // Register user with hashed password
    public User registerUser(User user) {
=======
    // Register user with validation
    public User registerUser(User user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new RuntimeException("Password cannot be empty");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already registered");
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken");
        }

>>>>>>> 078adae (Final changes to the project)
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

<<<<<<< HEAD
=======
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

>>>>>>> 078adae (Final changes to the project)
    public User updateProfile(String email, String newUsername, String newPassword, String newImage) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
<<<<<<< HEAD
            if (newUsername != null) user.setUsername(newUsername);
            if (newPassword != null && !newPassword.isEmpty()) {
                user.setPassword(passwordEncoder.encode(newPassword));
            }
            if (newImage != null) user.setProfileImage(newImage);
            return userRepository.save(user); // ✅ RETURN User
=======

            if (newUsername != null && !newUsername.isEmpty()) {
                user.setUsername(newUsername);
            }

            if (newPassword != null && !newPassword.isEmpty()) {
                user.setPassword(passwordEncoder.encode(newPassword));
            }

            if (newImage != null) {
                user.setProfileImage(newImage);
            }

            return userRepository.save(user);
>>>>>>> 078adae (Final changes to the project)
        }
        throw new RuntimeException("User not found");
    }

<<<<<<< HEAD

    // Login: verify password using bcrypt comparison
=======
>>>>>>> 078adae (Final changes to the project)
    public boolean loginUser(String email, String rawPassword) {
        return userRepository.findByEmail(email)
                .map(user -> passwordEncoder.matches(rawPassword, user.getPassword()))
                .orElse(false);
    }
}
