package spaiker_grn.github.com.restaurants_menu.Json_GsonParser;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;

public class GsonListParser implements IListParser {

    private final InputStream mInputStream;


    public GsonListParser(InputStream pInputStream) {
        mInputStream = pInputStream;
    }

    @Override
    public IListItem parse() throws Exception {
        Reader reader = new InputStreamReader(mInputStream);
        GsonItem[] result = new Gson().fromJson(reader, GsonItem[].class);
        return new GsonListItem(Arrays.asList(result));
    }

    public IListItem parseWithRoot() throws Exception{

        Reader reader = new InputStreamReader(mInputStream);

        return new Gson().fromJson(reader,GsonListItem.class);


    }
}
