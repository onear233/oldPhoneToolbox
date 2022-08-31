package com.oldphonetoolbox.frame.function.classreader;

import android.content.Context;


import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dalvik.system.DexFile;

/**
 * 类读取器<br/>
 * 读取某个包下所有类<br/>
 */
public final class ClassesReader {

    /**
     * @param packageName 包名
     * @return List<Class>    包下所有类
     */
    public static List<Class<?>> getClasses(String packageCodePath,String packageName) throws ClassNotFoundException,IOException {
        DexFile df = new DexFile(packageCodePath);//通过DexFile查找当前的APK中可执行文件
        Enumeration<String> enumeration = df.entries();//获取df中的元素  这里包含了所有可执行的类名 该类名包含了包名+类名的方式
        List<Class<?>> classes = new ArrayList<>();
        while(enumeration.hasMoreElements()){
            String  className = enumeration.nextElement();
            if (className.contains(packageName)) {
                classes.add(Class.forName(className));
            }
        }
        return classes;
    }
}