package spaiker_grn.github.com.restaurants_menu.Json_GsonParser;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;

public class GsonListParser implements IListParser {

    private final InputStream mInputStream;

    public GsonListParser(final InputStream pInputStream) {
        mInputStream = pInputStream;
    }

    @Override
    public GsonListItem parse() throws Exception {
        final Reader reader = new InputStreamReader(mInputStream);
        final GsonItem[] result = new Gson().fromJson(reader, GsonItem[].class);
        return new GsonListItem(Arrays.asList(result));
    }

    public GsonListItem parseWithRoot() throws Exception {

        final Reader reader = new InputStreamReader(mInputStream);
        return new Gson().fromJson(reader, GsonListItem.class);

    }
}
