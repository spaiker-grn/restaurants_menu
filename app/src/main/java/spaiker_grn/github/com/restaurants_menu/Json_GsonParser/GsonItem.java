package spaiker_grn.github.com.restaurants_menu.Json_GsonParser;

import com.google.gson.annotations.SerializedName;

public class GsonItem implements IItem {


    private String name;


    private String description;


    private String image_source;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getImage() {
        return image_source;
    }
}
