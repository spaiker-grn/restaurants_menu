package spaiker_grn.github.com.restaurants_menu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class UpdateBroadcastReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getStringExtra(Constants.BROADCAST_INTENT_KEY) != null) {
            if (Integer.parseInt(intent.getStringExtra(Constants.BROADCAST_INTENT_KEY)) != BuildConfig.VERSION_CODE) {
                Intent i = new Intent();
                i.setClassName("spaiker_grn.github.com.restaurants_menu", "spaiker_grn.github.com.restaurants_menu.UpdateActivity");
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                context.startActivity(i);

            }
        }


    }
}
