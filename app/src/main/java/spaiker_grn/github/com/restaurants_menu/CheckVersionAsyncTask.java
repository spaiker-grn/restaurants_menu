package spaiker_grn.github.com.restaurants_menu;

import android.os.AsyncTask;

class CheckVersionAsyncTask extends AsyncTask<String, String, String> {


    @Override
    protected String doInBackground(final String... params) {

        final CheckAppVersion checkAppVersion = new CheckAppVersion();
        checkAppVersion.checkVersion();

        return checkAppVersion.getVersion();
    }
}
