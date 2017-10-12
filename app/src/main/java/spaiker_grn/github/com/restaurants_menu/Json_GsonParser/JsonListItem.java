package spaiker_grn.github.com.restaurants_menu.Json_GsonParser;

import java.util.List;

public class JsonListItem implements IListItem {

    private List<JsonItem> mJsonItems;

    public JsonListItem(List<JsonItem> pList) {
        mJsonItems = pList;
    }

    @Override
    public List<JsonItem> getJsonList() {
        return mJsonItems;
    }

    @Override
    public List<GsonItem> getGsonList() {
        return null;
    }

}
