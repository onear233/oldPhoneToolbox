package com.oldphonetoolbox.onear.toolactivity.download;

import android.content.Context;

import com.oldphonetoolbox.onear.MainActivity;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class DownloaderThread implements Runnable {

    private final String name;
    private final String url;
    private final MainActivity activity;
    public DownloaderThread(String name, String url, MainActivity activity) {
        this.name = name;
        this.url = url;
        this.activity = activity;
    }
    @Override
    public void run() {
        //进行占位并获取索引
        int pointer = -1;
        for(int i = 0;i<3;i++){
            if(!DownloadProcess.isDownloading[i]){
                DownloadProcess.isDownloading[i] = true;
                DownloadProcess.name[i] = name;
                pointer = i;
                break;
            }
        }
        try(OutputStream out = activity.openFileOutput(name, Context.MODE_PRIVATE);
        InputStream is = new URL(url).openStream()){
            //设置缓冲区
            byte[] buffer = new byte[1024];
            int len = 0;
            int total = is.available();
            int download = 0;
            //读取流
            while((len = is.read(buffer))!=-1){
                out.write(buffer,0,len);
                download+=len;
                DownloadProcess.progress[pointer] = (int)((float)download/total*100);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
