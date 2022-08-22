package com.oldphonetoolbox.onear.toolactivity.download;

import android.util.Log;

import com.oldphonetoolbox.onear.MainActivity;
import com.oldphonetoolbox.onear.data.constant.socket.SocketConstantConfig;

import java.io.File;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

public class DownloadTransportThread implements Runnable{

    public static volatile Map<String,String> transportations = new HashMap<>();
    private final MainActivity activity;
    private final String toIp;
    public DownloadTransportThread(MainActivity activity,String toIp){
        this.toIp = toIp;
        this.activity = activity;
    }

    @Override
    public void run() {
        Map<String,String> willTransportations;
        willTransportations = transportations;
        transportations = new HashMap<>();
        Log.i(DownloadTransportThread.class.getName(), "传输数据获取成功");
        //创建通道
        try (SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(toIp, SocketConstantConfig.DOWNLOAD_PORT))) {
            //传输数量
            byte[] count = {(byte) willTransportations.size()};
            socketChannel.write(ByteBuffer.wrap(count));
            Thread.sleep(100);//等待处理
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.i(DownloadTransportThread.class.getName(), "准备开始传输文件");
        for (Map.Entry<String, String> entry : willTransportations.entrySet()) {
            transport(entry.getKey(),entry.getValue());
        }
        Log.i(DownloadTransportThread.class.getName(), "所有文件传输成功");
    }
    private void transport(String name,String url){
        //建立输入流
        try (InputStream stream = activity.openFileInput(url);
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(toIp,SocketConstantConfig.DOWNLOAD_PORT))
        ) {
            byte[] nameBytes = name.getBytes();
            byte[] lengthBytes = new byte[2];
            for (int i = 0; i < 2; i++) {
                int offset = (lengthBytes.length - 1 - i) * 8;
                lengthBytes[i] = (byte) (((short)nameBytes.length >>> offset) & 0xff);
            }
            socketChannel.write(ByteBuffer.wrap(lengthBytes));
            socketChannel.write(ByteBuffer.wrap(nameBytes));
            //设置缓冲区
            byte[] buffer = new byte[1024];
            int len;
            //读取流
            while ((len = stream.read(buffer)) != -1) {
                socketChannel.write(ByteBuffer.wrap(buffer, 0, len));
            }
            Log.i("通信传输", "一个文件传输成功 文件名"+name);
            File file = new File(activity.getFilesDir(), name);
            if(!file.delete()){
                Log.e("通信传输", "文件删除失败，文件名:"+name);
            }
        }catch (Exception e){
            Log.e(SocketConstantConfig.SOCKET_TAG, "数据传输异常 错误信息:"+e.getMessage());
        }
    }
}