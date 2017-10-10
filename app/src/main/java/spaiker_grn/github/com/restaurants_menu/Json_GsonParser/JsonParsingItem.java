package spaiker_grn.github.com.restaurants_menu.Json_GsonParser;

import org.json.JSONObject;

public class JsonParsingItem implements IParsingItem {

    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE_RESOURCE = "image_source";

    JSONObject mJSONObject;

    JsonParsingItem(JSONObject pJSONObject){

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
