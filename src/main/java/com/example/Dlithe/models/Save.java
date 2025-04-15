package com.example.Dlithe.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "saves")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Save {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String articleId;
    
    @Column(length = 500)
    private String title;
    
    @Column(length = 2000)
    private String description;
    
    @Column(length = 1000)
    private String url;
    
    @Column(length = 1000)
    private String imageUrl;
    
    @Column(length = 50)
    private String publishedAt;
    
    @Column(length = 100)
    private String sourceName;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
