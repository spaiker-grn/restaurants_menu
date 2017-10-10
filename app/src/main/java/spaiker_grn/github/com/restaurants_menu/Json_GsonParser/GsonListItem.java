package spaiker_grn.github.com.restaurants_menu.Json_GsonParser;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GsonListItem implements IListItem {


    @SerializedName("items")
    private List<GsonItem> mGsonItems;

    public GsonListItem (List<GsonItem> pGsonItems){
        mGsonItems = pGsonItems;
    }

    public List<GsonItem> getGsonList(){return  mGsonItems;}
}
