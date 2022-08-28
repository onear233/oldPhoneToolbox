package com.oldphonetoolbox.onear.toolactivity.download;

import android.content.Context;
import android.util.Log;

import com.oldphonetoolbox.onear.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
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
        Log.i("DOWNLOAD", "占位成功");

        URL u;
        HttpURLConnection conn;
        try {
            u = new URL(url);
            conn = (HttpURLConnection) u.openConnection();
            conn.setConnectTimeout(3 * 1000);
            //防止屏蔽程序抓取而放回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try(OutputStream out = activity.openFileOutput(name, Context.MODE_PRIVATE);
        InputStream is = conn.getInputStream()){
            Log.i("DOWNLOAD", "开始下载 链接"+url);
            //设置缓冲区
            byte[] buffer = new byte[1024];
            int len;
            int total = conn.getContentLength();
            int download = 0;
            System.out.println("total:"+total);
            //读取流
            while((len = is.read(buffer))!=-1){
                out.write(buffer,0,len);
                download+=len;
                DownloadProcess.progress[pointer] = (int)((float)download/total*100);
            }
            Log.i("DOWNLOAD", name+"下载完毕");
            //重置
            DownloadProcess.isDownloading[pointer] = false;
            DownloadProcess.name[pointer] = "";
            DownloadProcess.progress[pointer] = 0;
            DownloadTransportThread.transportations.put(name,name);
            Log.i("DOWNLOAD", "数据清除完毕");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
