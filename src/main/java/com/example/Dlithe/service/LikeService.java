package com.example.Dlithe.service;

import com.example.Dlithe.models.Likes;
import com.example.Dlithe.models.User;
import com.example.Dlithe.models.ArticleDTO;
import com.example.Dlithe.repository.LikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {
    @Autowired
    private LikesRepository likesRepository;

    public Likes mapToLikeEntity(ArticleDTO articleDTO, User user) {
        Likes like = new Likes();
        like.setArticleId(articleDTO.getArticleId());
        like.setTitle(articleDTO.getTitle());
        like.setDescription(articleDTO.getDescription());
        like.setUrl(articleDTO.getUrl());
        like.setImageUrl(articleDTO.getUrlToImage());
        like.setPublishedAt(articleDTO.getPublishedAt());
        like.setSourceName(articleDTO.getSource());
        like.setUser(user);
        return like;
    }

    public Likes saveLike(Likes like) {
        return likesRepository.save(like);
    }

    public List<Likes> getLikesByUser(User user) {
        return likesRepository.findByUser(user);
    }
}
