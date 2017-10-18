package spaiker_grn.github.com.restaurants_menu.httpClient;

import java.io.InputStream;

public interface IHttpClient {

    void request (String url, HttpClient.ResponseListener pResponseListener);

}
