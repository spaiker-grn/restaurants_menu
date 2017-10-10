package spaiker_grn.github.com.httpClient;

import java.io.InputStream;


public class HttpClient implements IHttpClient {

    @Override
    public InputStream request(String url) {
        throw new IllegalStateException("HttpClient don`t implemented");
    }
}
