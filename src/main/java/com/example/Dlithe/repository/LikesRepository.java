package com.example.Dlithe.repository;

import com.example.Dlithe.models.Likes;
import com.example.Dlithe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    List<Likes> findByUser(User user);
}
