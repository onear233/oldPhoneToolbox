package com.oldphonetoolbox.onear.handler.translation;

import android.content.Intent;
import android.media.Image;

import com.oldphonetoolbox.onear.MainActivity;
import com.oldphonetoolbox.onear.handler.OPTBHandlerAbstract;
import com.oldphonetoolbox.onear.toolactivity.PictureDisplay;

public class DisplayExecutor extends OPTBHandlerAbstract {
    @Override
    protected void executeCode(byte[] data, MainActivity activity) {
        if (data != null){
            Intent intent = new Intent(activity, PictureDisplay.class);
            intent.putExtra("photo",data);
            activity.startActivity(intent);
        }
    }
}
