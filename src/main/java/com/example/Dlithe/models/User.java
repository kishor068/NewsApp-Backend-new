package com.example.Dlithe.models;

import jakarta.persistence.*;
import lombok.*;
<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;
=======
import com.fasterxml.jackson.annotation.JsonProperty;
>>>>>>> 078adae (Final changes to the project)

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

<<<<<<< HEAD
    @JsonIgnore
=======
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
>>>>>>> 078adae (Final changes to the project)
    @Column(nullable = false)
    private String password;

    @Lob
    @Column(nullable = true)
    private String profileImage;
}
