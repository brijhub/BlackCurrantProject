package com.example.brijesh.blackcurrantproject;

/**
 * Created by Brijesh on 3/19/2017.
 */

public class ListPozo {
    String image,title,id;

    public ListPozo()
    {

    }

    public ListPozo(String image,String title,String id)
    {
        this.image=image;
        this.title=title;
        this.id=id;

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
