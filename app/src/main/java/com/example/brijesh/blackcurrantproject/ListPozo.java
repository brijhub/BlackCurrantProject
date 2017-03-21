package com.example.brijesh.blackcurrantproject;

/**
 * Created by Brijesh on 3/19/2017.
 */

public class ListPozo {
    String image,title,id,description;

    public ListPozo()
    {

    }

    public ListPozo(String image,String title,String id,String description)
    {
        this.image=image;
        this.title=title;
        this.id=id;
        this.description=description;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
