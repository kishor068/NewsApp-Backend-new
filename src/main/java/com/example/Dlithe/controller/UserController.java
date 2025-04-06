package com.example.Dlithe.controller;

import com.example.Dlithe.models.User;
import com.example.Dlithe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173") // ✅ Allow frontend access
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ Register Endpoint
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    // ✅ Login Endpoint
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        boolean isAuthenticated = userService.loginUser(email, password);

        if (isAuthenticated) {
            Optional<User> userOpt = userService.getUserByEmail(email);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                Map<String, String> response = new HashMap<>();
                response.put("message", "Login successful");
                response.put("username", user.getUsername());
                response.put("email", user.getEmail());
                response.put("profileImage", user.getProfileImage() != null ? user.getProfileImage() : "");
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(404).body("User not found");
            }
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(401).body(response);
        }
    }

    // ✅ Get profile by email
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Update profile (username, password, image)
    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestParam String email, @RequestBody Map<String, String> updateData) {
        String newUsername = updateData.get("username");
        String newPassword = updateData.get("password");
        String newImage = updateData.get("profileImage");

        try {
            User updatedUser = userService.updateProfile(email, newUsername, newPassword, newImage);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("User not found");
        }
    }
}
