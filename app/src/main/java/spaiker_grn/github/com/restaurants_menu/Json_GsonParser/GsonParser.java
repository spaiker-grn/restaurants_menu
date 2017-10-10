package spaiker_grn.github.com.restaurants_menu.Json_GsonParser;

import com.google.gson.Gson;

public class GsonParser implements IParser {

    private final String mSource;
    private IItem mIItem = new GsonItem();

    public GsonParser(String pSource) {
        mSource = pSource;
    }

    @Override
    public IItem parse() throws Exception {

        return mIItem = new Gson().fromJson(mSource, mIItem.getClass());

    }
}
