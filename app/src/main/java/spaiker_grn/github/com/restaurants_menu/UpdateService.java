package spaiker_grn.github.com.restaurants_menu;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.concurrent.ExecutionException;

import spaiker_grn.github.com.restaurants_menu.Backend.CheckVersionAsyncTask;

public class UpdateService extends IntentService {

    String appVersion;


    public UpdateService() {
        super("myTread");
    }



    @Nullable
    @Override
    public IBinder onBind(final Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            appVersion = new CheckVersionAsyncTask().execute().get();
        } catch (final InterruptedException pE) {
            pE.printStackTrace();
        } catch (final ExecutionException pE) {
            pE.printStackTrace();
        }

        Intent intentSend = new Intent("spaiker_grn.github.com.restaurants_menu").putExtra(Constants.BROADCAST_INTENT_KEY, appVersion);
        sendBroadcast(intentSend);

    }
}
