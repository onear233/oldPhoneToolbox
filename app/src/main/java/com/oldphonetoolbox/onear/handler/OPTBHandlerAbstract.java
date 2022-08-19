package com.oldphonetoolbox.onear.handler;

import com.oldphonetoolbox.onear.MainActivity;

import java.nio.channels.SocketChannel;

public abstract class OPTBHandlerAbstract implements OPTBHandlerInterface{
    @Override
    public void execute(byte[] data, MainActivity activity) {
        activity.runOnUiThread(()->{
            executeCode(data,activity);
        });
    }
    protected abstract void executeCode(byte[] data, MainActivity activity );
}
