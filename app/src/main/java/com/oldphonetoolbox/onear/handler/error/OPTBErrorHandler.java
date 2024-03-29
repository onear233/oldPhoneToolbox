package com.oldphonetoolbox.onear.handler.error;

import android.util.Log;
import android.widget.Toast;

import com.oldphonetoolbox.onear.MainActivity;
import com.oldphonetoolbox.onear.handler.OPTBHandlerAbstract;


public class OPTBErrorHandler extends OPTBHandlerAbstract {

    @Override
    protected void executeCode(byte[] data, MainActivity activity ) {
        Log.i(OPTBErrorHandler.class.getName(), "错误的调用id，电脑端可能属于三方魔改版本，小心使用");
        Toast.makeText(activity, "错误的调用id，电脑端可能属于三方魔改版本，小心使用", Toast.LENGTH_LONG).show();
    }
}
