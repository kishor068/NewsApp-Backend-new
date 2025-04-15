package com.example.Dlithe.repository;

import com.example.Dlithe.models.Save;
import com.example.Dlithe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaveRepository extends JpaRepository<Save, Long> {
    List<Save> findByUser(User user);
}
