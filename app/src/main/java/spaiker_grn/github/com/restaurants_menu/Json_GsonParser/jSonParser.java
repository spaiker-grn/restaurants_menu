package spaiker_grn.github.com.restaurants_menu.Json_GsonParser;

import org.json.JSONObject;

public class jSonParser implements IParser {

    private final String mSource;

    public jSonParser(String pSource) {
        mSource = pSource;
    }

    @Override
    public  IParsingItem parse() throws Exception {

        final JSONObject jsonObject = new JSONObject(mSource);

        return new JsonParsingItem(jsonObject);

    }
}
