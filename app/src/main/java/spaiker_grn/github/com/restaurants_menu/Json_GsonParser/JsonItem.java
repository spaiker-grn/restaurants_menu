package spaiker_grn.github.com.restaurants_menu.Json_GsonParser;

import org.json.JSONObject;

public class JsonItem implements IItem {

    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE_RESOURCE = "imageSource";

    JSONObject mJSONObject;

    JsonItem(JSONObject pJSONObject){

        mJSONObject = pJSONObject;
    }


    @Override
    public String getName() {
        return mJSONObject.optString(NAME);
    }

    @Override
    public String getDescription() {
        return mJSONObject.optString(DESCRIPTION);
    }

    @Override
    public String getImage() {
        return mJSONObject.optString(IMAGE_RESOURCE);
    }
}
