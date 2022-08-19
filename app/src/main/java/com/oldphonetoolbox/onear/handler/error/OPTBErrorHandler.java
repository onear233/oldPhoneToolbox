package com.oldphonetoolbox.onear.handler.error;

import android.widget.Toast;

import com.oldphonetoolbox.onear.MainActivity;
import com.oldphonetoolbox.onear.handler.OPTBHandlerAbstract;
import com.oldphonetoolbox.onear.handler.OPTBHandlerInterface;

import java.nio.channels.SocketChannel;

public class OPTBErrorHandler extends OPTBHandlerAbstract {

    @Override
    protected void executeCode(byte[] data, MainActivity activity ) {
        Toast.makeText(activity, "错误的调用id，电脑端可能属于三方魔改版本，小心使用", Toast.LENGTH_LONG).show();
    }
}
