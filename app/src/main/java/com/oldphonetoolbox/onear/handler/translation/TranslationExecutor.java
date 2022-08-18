package com.oldphonetoolbox.onear.handler.translation;

import android.util.Log;

import com.oldphonetoolbox.onear.MainActivity;
import com.oldphonetoolbox.onear.data.constant.socket.SocketConstantConfig;
import com.oldphonetoolbox.onear.handler.OPTBHandlerAbstract;

import java.nio.channels.SocketChannel;

public class TranslationExecutor extends OPTBHandlerAbstract {
    @Override
    protected void executeCode(byte[] data, MainActivity activity, SocketChannel socketChannel) {
        String in = new String(data);
        Log.e(SocketConstantConfig.SOCKET_TAG, in );
    }
}
