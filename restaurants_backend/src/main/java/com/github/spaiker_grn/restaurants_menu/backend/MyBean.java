package com.github.spaiker_grn.restaurants_menu.backend;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * The object model for the data we are sending through endpoints
 */


@Entity
public class MyBean {

    @Id
    private String name;
    private String description;
    private String imageSource;

    public MyBean(final String pName, final String pDescription, final String pImageSource) {
        name = pName;
        description = pDescription;
        imageSource = pImageSource;
    }
    public MyBean(){

    }

    public String getName() {
        return name;
    }

    public void setName(final String pName) {
        name = pName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String pDescription) {
        description = pDescription;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(final String pImageSource) {
        imageSource = pImageSource;
    }

}