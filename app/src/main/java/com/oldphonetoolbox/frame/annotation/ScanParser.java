package com.oldphonetoolbox.frame.annotation;

import android.content.Context;
import android.util.Log;

import com.oldphonetoolbox.frame.function.classreader.ClassesReader;
import com.oldphonetoolbox.onear.handler.OPTBHandlerCache;
import com.oldphonetoolbox.onear.handler.OPTBHandlerInterface;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public final class ScanParser {
    public static void parseConfig(Class<?> config, Context context) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        //获取路径
        try {
            //获取所有的类并遍历
            for (Class<?> aClass : ClassesReader.getClasses(context.getPackageCodePath(),config.getAnnotation(HandlerScan.class).value())) {
                injectHandler(aClass);
            }
        } catch (ClassNotFoundException|IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    private static void injectHandler(Class<?> handler) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Handler annotation = handler.getAnnotation(Handler.class);
        if(annotation==null){
            return;
        }
        byte value = annotation.value();
        Log.i("数据注入", "id:"+value+"------->handler:"+handler.getName());
        OPTBHandlerCache.setHandler(value, (OPTBHandlerInterface) handler.getDeclaredConstructor().newInstance());
    }
}
