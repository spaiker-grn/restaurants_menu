package spaiker_grn.github.com.restaurants_menu;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Matchers;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;
import java.io.InputStream;
import spaiker_grn.github.com.MockStream.MockStream;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.Time;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.TypeAdapterTime;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.GsonListParser;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.GsonParser;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.IListItem;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.IListParser;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.IParser;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.IItem;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.JsonListParser;
import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.jSonParser;
import spaiker_grn.github.com.restaurants_menu.httpClient.IHttpClient;
import spaiker_grn.github.com.restaurants_menu.httpClient.IHttpClientTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)

public class JsonTests {


    private static final String EXPECTED_NAME = "Coffee";
    private static final String EXPECTED_DESCRIPTION = "A couple of coffee prepared from roasted coffee beans";
    private static final String EXPECTED_IMAGE = "http://Example";
    private IHttpClientTest mIHttpClient;



    @Test
    public void parse() throws Exception {

        final InputStream inputstream = MockStream.sStream("jsonItem.json");
        assertNotNull("inputstream null", inputstream);
        when(mIHttpClient.request(Matchers.anyString())).thenReturn(inputstream);
        final InputStream response = mIHttpClient.request("http://Url");
        assertNotNull("Response null", response);


        ArgumentCaptor<String> mStringArgumentCaptor = ArgumentCaptor.forClass(String.class); //ArgumentCaptor example
        verify(mIHttpClient).request(mStringArgumentCaptor.capture());
        assertEquals("http://Url",mStringArgumentCaptor.getValue());

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



    @Before
    public void setUp() {
        mIHttpClient = mock(IHttpClientTest.class);
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
    public void adapterTest(){
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Time.class, new TypeAdapterTime());
        final Gson gson = gsonBuilder.create();

        final Time time = new Time("12", "January", "2000", "23", "56", "45");
        final String str =   gson.toJson(time);
        ShadowLog.d("Gson", str);
        final Time parseTime = gson.fromJson(str, Time.class);
        ShadowLog.d("Time",parseTime.toString());


    }

}
