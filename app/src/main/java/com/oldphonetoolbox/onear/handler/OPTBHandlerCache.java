package com.oldphonetoolbox.onear.handler;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.oldphonetoolbox.onear.handler.error.OPTBErrorHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OPTBHandlerCache {
    private static final Map<Byte,OPTBHandlerInterface> handlers_map = new HashMap<>();
    private static final OPTBHandlerInterface ERROR = new OPTBErrorHandler();
    static{
        //注入handler数据
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static OPTBHandlerInterface getHandler(byte id){
        return Optional.ofNullable(handlers_map.get(id)).orElse(ERROR);
    }
    public static OPTBHandlerInterface addHandler(byte id,OPTBHandlerInterface handler){
        return handlers_map.put(id,handler);
    }
}
