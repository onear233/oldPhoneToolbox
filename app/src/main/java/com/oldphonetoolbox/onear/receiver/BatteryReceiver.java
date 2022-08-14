package com.oldphonetoolbox.onear.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.oldphonetoolbox.onear.MainActivity;

public class BatteryReceiver extends BroadcastReceiver {

    private int current;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){
            current = (intent.getIntExtra("level", 0)) * 100 / intent.getIntExtra("scale", 0);
            Log.d("Battery_Changed","目前电量值为" + current);
            refreshBattery();
        }

    }

    private void refreshBattery() {
        MainActivity.setBattery(current);
    }
}
