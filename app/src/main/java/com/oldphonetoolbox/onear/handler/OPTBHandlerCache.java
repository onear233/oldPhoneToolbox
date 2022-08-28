package com.oldphonetoolbox.onear.handler;

import android.util.Log;

import com.oldphonetoolbox.onear.data.constant.socket.SocketConstantConfig;
import com.oldphonetoolbox.onear.data.constant.socket.SocketHandlerConfig;
import com.oldphonetoolbox.onear.handler.download.DownloadHandler;
import com.oldphonetoolbox.onear.handler.download.SendFileHandler;
import com.oldphonetoolbox.onear.handler.error.OPTBErrorHandler;
import com.oldphonetoolbox.onear.handler.monitor.MonitorHandler;
import com.oldphonetoolbox.onear.handler.translation.TranslationExecutor;
import com.oldphonetoolbox.onear.socket.SocketCoreController;

import java.util.HashMap;
import java.util.Map;

public class OPTBHandlerCache {
    private static final Map<Byte,OPTBHandlerInterface> handlers_map = new HashMap<>();
    private static final OPTBHandlerInterface ERROR = new OPTBErrorHandler();
    static{
        //注入handler数据
        handlers_map.put(SocketHandlerConfig.HANDLER_TRANSLATION.getId(),new TranslationExecutor());
        handlers_map.put(SocketHandlerConfig.BACK_TO_MAIN.getId(),(data,activity)->{
            if(SocketCoreController.optbActivityCompat!=null){
                SocketCoreController.optbActivityCompat.back();
                SocketCoreController.optbActivityCompat = null;
            }
        });
        handlers_map.put(SocketHandlerConfig.MONITOR.getId(),new MonitorHandler());
        handlers_map.put(SocketHandlerConfig.DOWNLOAD.getId(),new DownloadHandler());
        handlers_map.put(SocketHandlerConfig.TRANSPORT.getId(), new SendFileHandler());
    }
    public static OPTBHandlerInterface getHandler(byte id){
        OPTBHandlerInterface anInterface = handlers_map.get(id);
        if(anInterface!=null){
            Log.i(SocketConstantConfig.SOCKET_TAG, "获取到handler");
            return anInterface;
        }
        Log.e(SocketConstantConfig.SOCKET_TAG, "一次错误的id调用 id->"+id);
        return ERROR;
    }
}
