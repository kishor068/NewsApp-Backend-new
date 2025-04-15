package com.example.Dlithe.models;

public class ArticleDTO {
    private String articleId;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String source;

    // Getters and Setters
    public String getArticleId() { return articleId; }
    public void setArticleId(String articleId) { this.articleId = articleId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getUrlToImage() { return urlToImage; }
    public void setUrlToImage(String urlToImage) { this.urlToImage = urlToImage; }

    public String getPublishedAt() { return publishedAt; }
    public void setPublishedAt(String publishedAt) { this.publishedAt = publishedAt; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
}
