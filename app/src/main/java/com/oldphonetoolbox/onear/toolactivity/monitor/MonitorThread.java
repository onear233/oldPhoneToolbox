package com.oldphonetoolbox.onear.toolactivity.monitor;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.oldphonetoolbox.onear.data.constant.socket.SocketConstantConfig;
import com.oldphonetoolbox.onear.data.pojo.WindowsBean;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class MonitorThread implements Runnable{
    private final MonitorActivityCompat activity;
    public static boolean isRunning = true;
    public MonitorThread(MonitorActivityCompat activity) {
        this.activity = activity;
    }

    @Override
    public void run() {
        try {
            isRunning = true;
            System.out.println("执行线程");
            activity.serverSocketChannel = ServerSocketChannel.open();
            activity.serverSocketChannel.socket().bind(new InetSocketAddress(SocketConstantConfig.LISTEN_PORT));
            SocketChannel accept = activity.serverSocketChannel.accept();
            System.out.println("建立连接");
            ByteBuffer byteBuffer = ByteBuffer.allocate(2);
            while (true) {
                accept.read(byteBuffer);
                byte[] array = byteBuffer.array();
                byteBuffer.clear();
                ByteBuffer buffer = ByteBuffer.allocate(array[0] << 8 | array[1]);
                accept.read(buffer);
                byte[] bytes = buffer.array();
                activity.runOnUiThread(() -> {
                    activity.setData(WindowsBean.build(new String(bytes)));
                });
                //等待设置
                if(!(isRunning)){
                    break;
                }
            }
            accept.close();
            activity.serverSocketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
