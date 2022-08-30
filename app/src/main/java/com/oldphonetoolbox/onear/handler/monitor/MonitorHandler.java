package com.oldphonetoolbox.onear.handler.monitor;

import android.content.Intent;

import com.oldphonetoolbox.frame.annotation.Handler;
import com.oldphonetoolbox.onear.MainActivity;
import com.oldphonetoolbox.onear.handler.OPTBHandlerAbstract;
import com.oldphonetoolbox.onear.toolactivity.monitor.MonitorActivityCompat;

@Handler(0x02)
public class MonitorHandler extends OPTBHandlerAbstract {
    @Override
    protected void executeCode(byte[] data, MainActivity activity) {
        Intent intent = new Intent(activity, MonitorActivityCompat.class);
        activity.startActivity(intent);
    }
}
