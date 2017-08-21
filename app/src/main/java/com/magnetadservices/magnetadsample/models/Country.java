package com.magnetadservices.magnetadsample.models;

/**
 * Created by Esmaeili on 2017-08-19.
 */

public class Country {
    private String name;
    private String description;
    private int logo;
    private int image;

    public Country(String name, int logo) {
        this.name = name;
        this.logo = logo;
    }

    public Country(String name, String description, int logo, int image) {
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
