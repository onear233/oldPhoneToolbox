package com.oldphonetoolbox.onear.handler;

import java.util.HashMap;
import java.util.Map;

public class OPTBHandlerChache {
    private static final Map<Byte,OPTBHandlerInterface> handlers_map = new HashMap<>();
    static{
        //注入handler数据
    }
    public static OPTBHandlerInterface getHandler(byte id){
        return handlers_map.get(id);
    }
    public static OPTBHandlerInterface addHandler(byte id,OPTBHandlerInterface handler){
        return handlers_map.put(id,handler);
    }
}
