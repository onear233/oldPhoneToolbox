package com.oldphonetoolbox.onear.function.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SIO {
    public static String getStringByFile(File file) throws FileNotFoundException {
        return getStringByStream(new FileInputStream(file));
    }
    public static String getStringByStream(InputStream inputStream){
        StringBuilder sb = new StringBuilder();
        int len = -1;
        byte[] buf = new byte[1024];
        try {
            while ((len = inputStream.read(buf)) != -1) {
                sb.append(new String(buf, 0, len));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
