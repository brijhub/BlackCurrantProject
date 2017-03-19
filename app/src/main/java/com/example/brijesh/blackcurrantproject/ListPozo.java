package com.example.brijesh.blackcurrantproject;

/**
 * Created by Brijesh on 3/19/2017.
 */

public class ListPozo {
    String image,title,information;

    public ListPozo()
    {

    }

    public ListPozo(String image,String title)
    {
        this.image=image;
        this.title=title;
       // this.information=information;
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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
