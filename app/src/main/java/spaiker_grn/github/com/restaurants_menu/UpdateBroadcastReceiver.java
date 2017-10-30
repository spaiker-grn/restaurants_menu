package spaiker_grn.github.com.restaurants_menu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class UpdateReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getStringExtra(BROADCAST_INTENT_KEY) != null) {
            if (Integer.parseInt(intent.getStringExtra(BROADCAST_INTENT_KEY)) != BuildConfig.VERSION_CODE) {
                startActivity(new Intent(context, UpdateActivity.class));
            }
        }


    }
}
