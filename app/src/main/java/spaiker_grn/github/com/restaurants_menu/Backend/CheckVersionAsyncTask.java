package spaiker_grn.github.com.restaurants_menu.Backend;

import android.os.AsyncTask;

public class CheckVersionAsyncTask extends AsyncTask<String, String, String> {


    @Override
    protected String doInBackground(final String... params) {

        final CheckAppVersion checkAppVersion = new CheckAppVersion();
        checkAppVersion.checkVersion();

        return checkAppVersion.getVersion();
    }
}
