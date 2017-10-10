package spaiker_grn.github.com.restaurants_menu.Json_GsonParser;

import com.google.gson.Gson;

public class GsonParser implements IParser {

    private final String mSource;
    private IParsingItem iParsingItem = new GsonParsingItem();

    public GsonParser(String pSource) {
        mSource = pSource;
    }

    @Override
    public IParsingItem parse() throws Exception {

        return iParsingItem = new Gson().fromJson(mSource, iParsingItem.getClass());

    }
}
