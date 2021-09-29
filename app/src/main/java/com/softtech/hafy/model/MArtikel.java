package com.softtech.hafy.model;

public class MArtikel {
    //atribut
    private String keyArticle;
    private String articleTitle;
    private String articleCategory;
    private String articleImageUrl;
    private Object articleContent;
    private String articleWriter;
    private String datePublished;
    private Boolean isFeatured;
    private String articleTag;

    public MArtikel() {
        //
    }

    public MArtikel(String keyArticle, String articleTitle, String articleCategory, String articleImageUrl, Object articleContent, String articleWriter, String datePublished, Boolean isFeatured, String articleTag) {
        this.keyArticle = keyArticle;
        this.articleTitle = articleTitle;
        this.articleCategory = articleCategory;
        this.articleImageUrl = articleImageUrl;
        this.articleContent = articleContent;
        this.articleWriter = articleWriter;
        this.datePublished = datePublished;
        this.isFeatured = isFeatured;
        this.articleTag = articleTag;
    }

    public String getKeyArticle() {
        return keyArticle;
    }

    public void setKeyArticle(String keyArticle) {
        this.keyArticle = keyArticle;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(String articleCategory) {
        this.articleCategory = articleCategory;
    }

    public String getArticleImageUrl() {
        return articleImageUrl;
    }

    public void setArticleImageUrl(String articleImageUrl) {
        this.articleImageUrl = articleImageUrl;
    }

    public Object getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(Object articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleWriter() {
        return articleWriter;
    }

    public void setArticleWriter(String articleWriter) {
        this.articleWriter = articleWriter;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public Boolean getFeatured() {
        return isFeatured;
    }

    public void setFeatured(Boolean featured) {
        isFeatured = featured;
    }

    public String getArticleTag() {
        return articleTag;
    }

    public void setArticleTag(String articleTag) {
        this.articleTag = articleTag;
    }
}
