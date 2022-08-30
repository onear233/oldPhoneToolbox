package com.oldphonetoolbox.onear.handler;

import android.util.Log;

import com.oldphonetoolbox.onear.data.constant.socket.SocketConstantConfig;
import com.oldphonetoolbox.onear.handler.error.OPTBErrorHandler;

import java.util.HashMap;
import java.util.Map;

public class OPTBHandlerCache {
    private static final Map<Byte,OPTBHandlerInterface> handlers_map = new HashMap<>();
    private static final OPTBHandlerInterface ERROR = new OPTBErrorHandler();
    public static OPTBHandlerInterface getHandler(byte id){
        OPTBHandlerInterface anInterface = handlers_map.get(id);
        if(anInterface!=null){
            Log.i(SocketConstantConfig.SOCKET_TAG, "获取到handler");
            return anInterface;
        }
        Log.e(SocketConstantConfig.SOCKET_TAG, "一次错误的id调用 id->"+id);
        return ERROR;
    }
    public static void setHandler(byte id,OPTBHandlerInterface handler){
        handlers_map.put(id,handler);
    }
}
