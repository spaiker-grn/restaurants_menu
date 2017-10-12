package spaiker_grn.github.com.restaurants_menu;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.io.InputStream;

import spaiker_grn.github.com.MockStream.MockStream;
import spaiker_grn.github.com.httpClient.IHttpClient;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.AdapterForTime;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.GsonListParser;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.GsonParser;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.IListItem;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.IListParser;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.IParser;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.IItem;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.JsonListParser;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.jSonParser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)

public class JsonTests {


    private static final String EXPECTED_NAME = "Coffee";
    private static final String EXPECTED_DESCRIPTION = "A couple of coffee prepared from roasted coffee beans";
    private static final String EXPECTED_IMAGE = "http://Example";

    @Test
    public void parse() throws Exception {

        final InputStream inputstream = MockStream.sStream("jsonItem.json");
        assertNotNull("inputstream null", inputstream);
        when(mIHttpClient.request(Matchers.anyString())).thenReturn(inputstream);
        final InputStream response = mIHttpClient.request("http://Url");
        assertNotNull("Response null", response);

        final IParser iParser = new GsonParser(response);
        final IItem iItem = iParser.parse();

        assertEquals(EXPECTED_NAME, iItem.getName());
        assertEquals(EXPECTED_DESCRIPTION, iItem.getDescription());
        assertEquals(EXPECTED_IMAGE, iItem.getImage());

        final InputStream inputStream = MockStream.sStream("jsonItem.json");
        assertNotNull("inputstream null", inputStream);
        when(mIHttpClient.request(Matchers.anyString())).thenReturn(inputStream);
        final InputStream request = mIHttpClient.request("http://Url");
        assertNotNull("Response null", request);

        final IParser iParserJson = new jSonParser(request);
        final IItem iItemJson = iParserJson.parse();

        assertEquals(EXPECTED_NAME, iItemJson.getName());
        assertEquals(EXPECTED_DESCRIPTION, iItemJson.getDescription());
        assertEquals(EXPECTED_IMAGE, iItemJson.getImage());

    }

    private IHttpClient mIHttpClient;

    @Before
    public void setUp() {
        mIHttpClient = mock(IHttpClient.class);
        ShadowLog.stream = System.out;
    }

    @Test
    public void parseListGson() throws Exception {

        final InputStream inputstream = MockStream.sStream("jsonListItem.json");
        assertNotNull("inputstream null", inputstream);
        when(mIHttpClient.request(Matchers.anyString())).thenReturn(inputstream);
        final InputStream response = mIHttpClient.request("http://Url");
        assertNotNull("Response null", response);

        final IListParser iGsonListParser = new GsonListParser(response);
        final IListItem iListItem = iGsonListParser.parse();
        assertTrue(iListItem.getGsonList().size() == 3);
        assertEquals(iListItem.getGsonList().get(0).getName(), "Coffee");
        assertEquals(iListItem.getGsonList().get(0).getImage(), "http://Example");

        ShadowLog.d("Name 1: ", iListItem.getGsonList().get(0).getName());
        ShadowLog.d("Name 2: ", iListItem.getGsonList().get(1).getName());

        final InputStream inputstreamWithRoot = MockStream.sStream("jsonListItemRoot.json");
        assertNotNull("inputstream null", inputstreamWithRoot);
        when(mIHttpClient.request(Matchers.anyString())).thenReturn(inputstreamWithRoot);
        final InputStream responseWithRoot = mIHttpClient.request("http://Url");
        assertNotNull("Response null", response);

        final IListParser WithRoot = new GsonListParser(responseWithRoot);
        final IListItem iListItemWithRoot = WithRoot.parseWithRoot();

        assertTrue(iListItemWithRoot.getGsonList().size() == 3);
        assertEquals(iListItemWithRoot.getGsonList().get(0).getName(), "Coffee");
        assertEquals(iListItemWithRoot.getGsonList().get(0).getImage(), "http://Example");

        ShadowLog.d("Name 1: ", iListItemWithRoot.getGsonList().get(0).getName());
        ShadowLog.d("Name 2: ", iListItemWithRoot.getGsonList().get(1).getName());

    }

    @Test
    public void parseListJson() throws Exception {

        final InputStream inputstream = MockStream.sStream("jsonListItemRoot.json");
        assertNotNull("inputstream null", inputstream);
        when(mIHttpClient.request(Matchers.anyString())).thenReturn(inputstream);
        final InputStream response = mIHttpClient.request("http://Url");
        assertNotNull("Response null", response);

        final IListParser jsonListParser = new JsonListParser(response);
        final IListItem jsonListItem = jsonListParser.parse();


        assertTrue(jsonListItem.getJsonList().size()==3);
        assertEquals(jsonListItem.getJsonList().get(0).getName(), "Coffee");
        assertEquals(jsonListItem.getJsonList().get(1).getName(), "Black tea");

        ShadowLog.d("Name 1: ", jsonListItem.getJsonList().get(0).getName());
        ShadowLog.d("Name 2: ", jsonListItem.getJsonList().get(1).getName());

    }

    @Test
    public  void parseTime() throws AdapterForTime.UncorrectedDateType {

        String response = "12042007234554";
        AdapterForTime adapterForTime = new AdapterForTime(response);
        ShadowLog.d("parsing time", adapterForTime.getOutPut());

    }

}
