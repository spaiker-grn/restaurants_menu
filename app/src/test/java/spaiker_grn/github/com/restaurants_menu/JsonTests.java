package spaiker_grn.github.com.restaurants_menu;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.io.InputStream;

import spaiker_grn.github.com.Mocks;
import spaiker_grn.github.com.httpClient.IHttpClient;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.GsonListParser;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.GsonParser;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.IListItem;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.IParser;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.IItem;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.jSonParser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


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
        final IItem iItem = iParser.parse();


        assertEquals(EXPECTED_NAME, iItem.getName());
        assertEquals(EXPECTED_DESCRIPTION, iItem.getDescription());
        assertEquals(EXPECTED_IMAGE, iItem.getImage());

        final IParser iParserJson = new jSonParser(STRING_SOURCE);
        final IItem iItemJson = iParserJson.parse();

        assertEquals(EXPECTED_NAME, iItemJson.getName());
        assertEquals(EXPECTED_DESCRIPTION, iItemJson.getDescription());
        assertEquals(EXPECTED_IMAGE, iItemJson.getImage());


    }


    private IHttpClient mIHttpClient;


    @Before
    public void setUp(){
        mIHttpClient = mock(IHttpClient.class);
        ShadowLog.stream = System.out;
    }

    @Test
    public void parseList() throws Exception{

        final InputStream inputstream = Mocks.sStream("jsonListItem.json");
        assertNotNull("inputstream null", inputstream);
        when(mIHttpClient.request(Matchers.anyString())).thenReturn(inputstream);
        final InputStream response = mIHttpClient.request("http://Url");
        assertNotNull("Response null", response);

        final GsonListParser gsonListParser = new GsonListParser(response);
        final IListItem listItem = gsonListParser.parse();
        assertTrue(listItem.getGsonList().size()==3);
        assertEquals(listItem.getGsonList().get(0).getName(),"Coffee");
        assertEquals(listItem.getGsonList().get(0).getImage(), "http://Example");


        ShadowLog.d("Name 1: ", listItem.getGsonList().get(0).getName());
        ShadowLog.d("Name 2: ", listItem.getGsonList().get(1).getName());

        final InputStream inputstreamWithRoot = Mocks.sStream("jsonListItemRoot.json");
        assertNotNull("inputstream null", inputstreamWithRoot);
        when(mIHttpClient.request(Matchers.anyString())).thenReturn(inputstreamWithRoot);
        final InputStream responseWithRoot = mIHttpClient.request("http://Url");
        assertNotNull("Response null", response);


        final GsonListParser WithRoot = new GsonListParser(responseWithRoot);
        final IListItem listItemWithRoot = WithRoot.parseWithRoot();

        assertTrue(listItemWithRoot.getGsonList().size()==3);
        assertEquals(listItemWithRoot.getGsonList().get(0).getName(),"Coffee");
        assertEquals(listItemWithRoot.getGsonList().get(0).getImage(), "http://Example");


        ShadowLog.d("Name 1: ", listItemWithRoot.getGsonList().get(0).getName());
        ShadowLog.d("Name 2: ", listItemWithRoot.getGsonList().get(1).getName());



    }

}
