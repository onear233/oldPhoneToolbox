package com.oldphonetoolbox.onear.toolactivity.download;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.oldphonetoolbox.onear.MainActivity;
import com.oldphonetoolbox.onear.data.constant.socket.SocketConstantConfig;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

public class DownloadTransportThread implements Runnable{

    public static volatile Map<String,String> transportations = new HashMap<>();
    public static volatile boolean isRunning = false;
    private final MainActivity activity;
    public DownloadTransportThread(MainActivity activity){
        this.activity = activity;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void run() {
        Map<String,String> willTransportations;
        willTransportations = transportations;
        transportations = new HashMap<>();
        try (ServerSocketChannel socketChannel = ServerSocketChannel.open().bind(new InetSocketAddress(SocketConstantConfig.DOWNLOAD_PORT));
            SocketChannel accept = socketChannel.accept();) {
            willTransportations.forEach((k,v)->transport(k,v,accept));
        }catch (Exception e){
            Log.e(SocketConstantConfig.SOCKET_TAG, "数据传输异常 错误信息:"+e.getMessage());
        }
        //等待连接
    }
    private void transport(String name,String url,SocketChannel socketChannel){
        //建立输入流
        try (InputStream stream = activity.openFileInput(url)) {

        }catch (Exception e){
            Log.e(SocketConstantConfig.SOCKET_TAG, "数据传输异常 错误信息:"+e.getMessage());
        }
    }
}