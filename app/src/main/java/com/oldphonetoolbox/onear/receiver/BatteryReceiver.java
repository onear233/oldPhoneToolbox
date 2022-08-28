package com.oldphonetoolbox.onear.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.oldphonetoolbox.onear.MainActivity;

public class BatteryReceiver extends BroadcastReceiver {

    private int current;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){
            current = (intent.getIntExtra("level", 0)) * 100 / intent.getIntExtra("scale", 0);
            refreshBattery();
        }

    }

    private void refreshBattery() {
        MainActivity.setBattery(current);
    }
}
