package spaiker_grn.github.com.restaurants_menu.Json_GsonParser;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class GsonParser implements IParser {


    private final InputStream mInputStream;
    private IItem mIItem = new GsonItem();

    public GsonParser(final InputStream pSource) { mInputStream = pSource;
    }

    @Override
    public IItem parse() throws Exception {

        final Reader reader = new InputStreamReader(mInputStream);
        return mIItem = new Gson().fromJson(reader, mIItem.getClass());

    }
}
