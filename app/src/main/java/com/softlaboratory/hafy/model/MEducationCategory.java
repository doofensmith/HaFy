package com.softlaboratory.hafy.model;

public class MEducationCategory {
    //attribut
    private String image;
    private String category;

    public MEducationCategory() {
        //
    }

    public MEducationCategory(String image, String category) {
        this.image = image;
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
