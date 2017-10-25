package spaiker_grn.github.com.restaurants_menu.Backend;

import android.net.Uri;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import spaiker_grn.github.com.restaurants_menu.Json_GsonParser.Version;
import spaiker_grn.github.com.restaurants_menu.httpClient.HttpClient;

class CheckAppVersion {

    private String version;

    public String getVersion() {
        return version;
    }

    void checkVersion() {

        final HttpClient.ResponseListener responseListenerVersion = new ResponseListenerVersion();
        new HttpClient().request(buildGet(), responseListenerVersion);

    }

    private String buildGet() {
        final Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.appendEncodedPath("/restaurants-menu-183209.appspot.com/_ah/api/myBeanApi/v1/myBean");
        builder.appendPath("default");
        return builder.build().toString();
    }

    private class ResponseListenerVersion implements HttpClient.ResponseListener {

        @Override
        public void onResponse(final InputStream pInputStream) {

            final Reader reader = new InputStreamReader(pInputStream);
            final Version vr = new Gson().fromJson(reader, Version.class);
            version = vr.getVersion();
        }

        @Override
        public void onError(final Throwable pThrowable) {

        }
    }

}
