package com.softlaboratory.hafy.model;

public class MHomeContainer {

    //ATTRIB
    private String title;

    //CONS
    public MHomeContainer() {
        //
    }

    public MHomeContainer(String title) {
        this.title = title;
    }

    //GET N SET
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
