package com.oldphonetoolbox.onear.handler.translation;

import android.content.Intent;
import android.util.Log;

import com.oldphonetoolbox.onear.MainActivity;
import com.oldphonetoolbox.onear.data.constant.socket.SocketConstantConfig;
import com.oldphonetoolbox.onear.handler.OPTBHandlerAbstract;
import com.oldphonetoolbox.onear.toolactivity.translation.TranslateActivity;

public class TranslationExecutor extends OPTBHandlerAbstract {
    @Override
    protected void executeCode(byte[] data, MainActivity activity ) {
        String in = new String(data);
        Log.i(SocketConstantConfig.SOCKET_TAG, in );
        Intent intent = new Intent(activity, TranslateActivity.class);
        //将英文in传过去
        intent.putExtra("english", in);
        activity.startActivity(intent);
    }
}
