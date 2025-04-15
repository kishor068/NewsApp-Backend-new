package com.example.Dlithe.service;

import com.example.Dlithe.models.Save;
import com.example.Dlithe.models.User;
import com.example.Dlithe.models.ArticleDTO;
import com.example.Dlithe.repository.SaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveService {
    @Autowired
    private SaveRepository saveRepository;

    public Save mapToSaveEntity(ArticleDTO articleDTO, User user) {
        Save save = new Save();
        save.setArticleId(articleDTO.getArticleId());
        save.setTitle(articleDTO.getTitle());
        save.setDescription(articleDTO.getDescription());
        save.setUrl(articleDTO.getUrl());
        save.setImageUrl(articleDTO.getUrlToImage());
        save.setPublishedAt(articleDTO.getPublishedAt());
        save.setSourceName(articleDTO.getSource());
        save.setUser(user);
        return save;
    }

    public Save saveArticle(Save save) {
        return saveRepository.save(save);
    }

    public List<Save> getSavesByUser(User user) {
        return saveRepository.findByUser(user);
    }
}
