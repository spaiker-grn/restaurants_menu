package spaiker_grn.github.com.restaurants_menu.Json_GsonParser;

import java.util.List;

public class GsonListItem implements IListItem {

    private List<GsonItem> mGsonItems;

    public GsonListItem (List<GsonItem> pGsonItems){
        mGsonItems = pGsonItems;
    }

    public List<GsonItem> getGsonList(){return  mGsonItems;}
}
