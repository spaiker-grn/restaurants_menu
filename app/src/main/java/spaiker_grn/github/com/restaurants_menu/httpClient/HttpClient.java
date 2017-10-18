package spaiker_grn.github.com.restaurants_menu.httpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient implements IHttpClient {

    private HttpURLConnection mConnection;

    @Override
    public void request(final String url, final ResponseListener pResponseListener) {

        try {
            final InputStream is = openStream(url);
            pResponseListener.onResponse(is);
            mConnection.disconnect();
        } catch (final Throwable t) {
            pResponseListener.onError(t);
        } finally {
            if (mConnection !=null){
                mConnection.disconnect();
            }
        }
    }

    private InputStream openStream(final String url) throws IOException {
        mConnection = (HttpURLConnection) (new URL(url)).openConnection();
        return mConnection.getInputStream();
    }


    public interface ResponseListener {
        void onResponse (InputStream pInputStream);
        void onError(Throwable pThrowable);
    }



}
