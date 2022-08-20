package com.oldphonetoolbox.onear.handler.picture;

import android.content.Intent;

import com.oldphonetoolbox.onear.MainActivity;
import com.oldphonetoolbox.onear.handler.OPTBHandlerAbstract;
import com.oldphonetoolbox.onear.toolactivity.picture.PictureDisplay;

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
