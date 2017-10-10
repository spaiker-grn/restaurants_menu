package spaiker_grn.github.com.restaurants_menu.Json_GsonParser;

import com.google.gson.annotations.SerializedName;

public class GsonParsingItem implements IParsingItem {

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("image_source")
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
