package spaiker_grn.github.com.restaurants_menu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.GsonParser;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.IParser;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.IParsingItem;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.jSonParser;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)


public class JsonTests {

    private static final String STRING_SOURCE = "{\n" +
            "\"name\" : \"Coffee\",\n" +
            "\"description\" : \"A couple of coffee prepared from roasted coffee beans\",\n" +
            "\"image_source\" : \"http://Example\"\n" +
"}";

    private static final String EXPECTED_NAME = "Coffee";
    private static final String EXPECTED_DESCRIPTION = "A couple of coffee prepared from roasted coffee beans";
    private static final String EXPECTED_IMAGE = "http://Example";

    @Test
    public void parse() throws Exception {

        final IParser iParser = new GsonParser(STRING_SOURCE);
        final IParsingItem iParsingItem = iParser.parse();


        assertEquals(EXPECTED_NAME, iParsingItem.getName());
        assertEquals(EXPECTED_DESCRIPTION, iParsingItem.getDescription());
        assertEquals(EXPECTED_IMAGE, iParsingItem.getImage());

        final IParser iParserJson = new jSonParser(STRING_SOURCE);
        final IParsingItem iParsingItemJson = iParserJson.parse();

        assertEquals(EXPECTED_NAME, iParsingItemJson.getName());
        assertEquals(EXPECTED_DESCRIPTION, iParsingItemJson.getDescription());
        assertEquals(EXPECTED_IMAGE, iParsingItemJson.getImage());

    }

}
