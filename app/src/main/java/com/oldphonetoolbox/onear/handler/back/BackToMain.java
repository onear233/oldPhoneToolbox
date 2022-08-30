package com.oldphonetoolbox.onear.handler.back;

import com.oldphonetoolbox.frame.annotation.Handler;
import com.oldphonetoolbox.onear.MainActivity;
import com.oldphonetoolbox.onear.handler.OPTBHandlerAbstract;
import com.oldphonetoolbox.onear.socket.SocketCoreController;

@Handler(0x01)
public class BackToMain extends OPTBHandlerAbstract {
    @Override
    protected void executeCode(byte[] data, MainActivity activity) {
        if(SocketCoreController.optbActivityCompat!=null){
            SocketCoreController.optbActivityCompat.back();
            SocketCoreController.optbActivityCompat = null;
        }
    }
}
