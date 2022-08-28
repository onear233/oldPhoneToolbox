package com.oldphonetoolbox.onear.socket;

import android.util.Log;
import android.widget.Toast;

import com.oldphonetoolbox.onear.*;
import com.oldphonetoolbox.onear.data.constant.socket.SocketConstantConfig;
import com.oldphonetoolbox.onear.handler.OPTBHandlerCache;
import com.oldphonetoolbox.onear.socket.tool.IpAddress;
import com.oldphonetoolbox.onear.toolactivity.OPTBActivityCompat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class SocketCoreController {

    //页面对象
    private MainActivity activity;
    public static SocketChannel socketChannel;
    private final ByteBuffer metaData = ByteBuffer.allocate(SocketConstantConfig.FIRST_LENGTH);
    public static OPTBActivityCompat optbActivityCompat;

    public void starter(MainActivity activity) throws IOException {
        this.activity = activity;
        //建立通道
        ServerSocketChannel socketChannelServer  = ServerSocketChannel.open();
        socketChannelServer.socket().bind(new InetSocketAddress(SocketConstantConfig.CHANNEL_PORT));
        while (SocketConstantConfig.IS_FLAG){
            String s = IpAddress.getIpAddress();
            //等待连接
            activity.runOnUiThread(()->{
                //获取本机局域网ip地址
                Log.i(SocketConstantConfig.SOCKET_TAG, "本机ip地址：" + s);
                Toast.makeText(activity, "ip地址:"+s, Toast.LENGTH_LONG).show();
            });
            Log.i(SocketConstantConfig.SOCKET_TAG, "等待连接中");
            socketChannel = socketChannelServer.accept();
            channelProcess();
        }
    }
    private void channelProcess() throws IOException {
        Log.i(SocketConstantConfig.SOCKET_TAG, "连接成功建立");
        while (SocketConstantConfig.IS_FLAG){
            //等待读取
            socketChannel.read(metaData);
            byte[] array = getMetaDataBytes(metaData);
            //获取携带数据长度
            ByteBuffer byteBuffer = ByteBuffer.allocate((array[0] & 0xFF) << 8 | (array[1] & 0xFF));
            socketChannel.read(byteBuffer);
            if(optbActivityCompat!=null){
                optbActivityCompat.back();
                optbActivityCompat = null;
            }
            Log.i(SocketConstantConfig.SOCKET_TAG, "接收到数据,数据为:"+array[0]+","+array[1]+","+array[2]);
            OPTBHandlerCache.getHandler(array[2]).execute(getMetaDataBytes(byteBuffer),activity);
        }
    }
    private byte[] getMetaDataBytes(ByteBuffer byteBuffer){
        byteBuffer.flip();
        byte[] array = byteBuffer.array();
        byteBuffer.clear();
        return array;
    }
}