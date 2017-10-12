package spaiker_grn.github.com.restaurants_menu.Json_GsonParser;

import org.json.JSONObject;
import java.io.InputStream;


public class jSonParser implements IParser {

    private final InputStream mSource;

    public jSonParser(InputStream pSource) {
        mSource = pSource;
    }


    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }


    @Override
    public IItem parse() throws Exception {

       String str = convertStreamToString(mSource);

        final JSONObject jsonObject = new JSONObject(str);
        return new JsonItem(jsonObject);

    }
}
