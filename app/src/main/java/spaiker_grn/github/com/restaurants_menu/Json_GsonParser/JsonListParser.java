package spaiker_grn.github.com.restaurants_menu.Json_GsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class JsonListParser implements IListParser {

    private InputStream mInputStream;

    public JsonListParser(final InputStream pInputStream) {
        mInputStream = pInputStream;
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    @Override
    public IListItem parse() throws Exception {

        final String str = convertStreamToString(mInputStream);

        final List<JsonItem> list = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(str);
        final JSONArray jsonArray = jsonObject.getJSONArray("items");

        for (int i = 0; i < jsonArray.length(); i++) {

            jsonObject = jsonArray.getJSONObject(i);

            list.add(new JsonItem(jsonObject));
        }

        return new JsonListItem(list);
    }

    @Override
    public IListItem parseWithRoot() throws Exception {
        return null;
    }
}
