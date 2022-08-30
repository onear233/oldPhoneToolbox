package com.oldphonetoolbox.frame.annotation;

import android.content.Context;

import com.oldphonetoolbox.frame.function.classreader.ClassesReader;
import com.oldphonetoolbox.onear.handler.OPTBHandlerCache;
import com.oldphonetoolbox.onear.handler.OPTBHandlerInterface;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public final class ScanParser {
    public static void parseConfig(Class<?> config, Context context) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        //获取路径
        String path = config.getAnnotation(HandlerScan.class).value();
        //获取所有的类
        List<Class<?>> classes = null;
        try {
            classes = ClassesReader.getClasses(context.getPackageCodePath(),path);
            for (Class<?> aClass : classes) {
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
        OPTBHandlerCache.setHandler(value, (OPTBHandlerInterface) handler.getDeclaredConstructor().newInstance());
    }
}
