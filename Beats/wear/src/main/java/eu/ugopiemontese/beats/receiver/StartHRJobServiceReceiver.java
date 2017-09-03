package eu.ugopiemontese.beats.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import eu.ugopiemontese.beats.utils.HRJobSchedule;

public class StartHRJobServiceReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        HRJobSchedule.scheduleJob(context);
    }

}

