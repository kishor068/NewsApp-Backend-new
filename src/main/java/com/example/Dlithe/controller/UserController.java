package com.example.Dlithe.controller;

<<<<<<< HEAD
import com.example.Dlithe.models.User;
import com.example.Dlithe.service.UserService;
=======
import com.example.Dlithe.models.Likes;
import com.example.Dlithe.models.Save;
import com.example.Dlithe.models.User;
import com.example.Dlithe.service.LikeService;
import com.example.Dlithe.service.SaveService;
import com.example.Dlithe.service.UserService;
import com.example.Dlithe.models.ArticleDTO;
>>>>>>> 078adae (Final changes to the project)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173") // ✅ Allow frontend access
=======
import java.util.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
>>>>>>> 078adae (Final changes to the project)
public class UserController {

    @Autowired
    private UserService userService;

<<<<<<< HEAD
    // ✅ Register Endpoint
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    // ✅ Login Endpoint
=======
    @Autowired
    private LikeService likeService;

    @Autowired
    private SaveService saveService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User savedUser = userService.registerUser(user);
            return ResponseEntity.ok(savedUser);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

>>>>>>> 078adae (Final changes to the project)
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

<<<<<<< HEAD
    // ✅ Get profile by email
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestParam String email) {
        return userService.getUserByEmail(email)
=======
    @GetMapping("/profile/{username}")
    public ResponseEntity<?> getProfile(@PathVariable String username) {
        return userService.getUserByUsername(username)
>>>>>>> 078adae (Final changes to the project)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

<<<<<<< HEAD
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
=======
    @PutMapping("/profile/{username}")
    public ResponseEntity<?> updateProfile(@PathVariable String username, @RequestBody Map<String, String> updateData) {
        try {
            Optional<User> userOpt = userService.getUserByUsername(username);
            if (userOpt.isEmpty()) {
                return ResponseEntity.status(404).body("User not found");
            }

            String newUsername = updateData.get("username");
            String newPassword = updateData.get("password");
            String newImage = updateData.get("profileImage");

            User updatedUser = userService.updateProfile(
                    userOpt.get().getEmail(), // Use email from found user
                    newUsername != null ? newUsername : username, // Keep current if not provided
                    newPassword,
                    newImage);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{username}/like-article")
    public ResponseEntity<?> likeArticle(@PathVariable("username") String username,
            @RequestBody ArticleDTO articleData) {
        Optional<User> userOpt = userService.getUserByUsername(username);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        Likes like = likeService.mapToLikeEntity(articleData, userOpt.get());
        Likes savedLike = likeService.saveLike(like);
        return ResponseEntity.ok(savedLike);
    }

    @PutMapping("{username}/save-article")
    public ResponseEntity<?> saveArticle(@PathVariable String username, @RequestBody ArticleDTO articleData) {
        Optional<User> userOpt = userService.getUserByUsername(username);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        Save save = saveService.mapToSaveEntity(articleData, userOpt.get());
        Save savedArticle = saveService.saveArticle(save);
        return ResponseEntity.ok(savedArticle);
    }

    @GetMapping("{username}/liked-articles")
    public ResponseEntity<List<Likes>> getLikedArticles(@PathVariable String username) {
        Optional<User> userOpt = userService.getUserByUsername(username);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Likes> likedArticles = likeService.getLikesByUser(userOpt.get());
        return ResponseEntity.ok(likedArticles);
    }

    @GetMapping("{username}/saved-articles")
    public ResponseEntity<List<Save>> getSavedArticles(@PathVariable String username) {
        Optional<User> userOpt = userService.getUserByUsername(username);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Save> savedArticles = saveService.getSavesByUser(userOpt.get());
        return ResponseEntity.ok(savedArticles);
    }
}
>>>>>>> 078adae (Final changes to the project)
