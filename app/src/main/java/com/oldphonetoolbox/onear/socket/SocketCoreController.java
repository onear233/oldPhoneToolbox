package com.oldphonetoolbox.onear.socket;

import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.oldphonetoolbox.onear.*;
import com.oldphonetoolbox.onear.data.constant.socket.SocketConstantConfig;
import com.oldphonetoolbox.onear.handler.OPTBHandlerChache;
import com.oldphonetoolbox.onear.handler.error.OPTBErrorHandler;
import com.oldphonetoolbox.onear.socket.tool.IpAddress;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Optional;

public class SocketCoreController {

    //页面对象
    private MainActivity activity;

    private final ByteBuffer metaData = ByteBuffer.allocate(SocketConstantConfig.FIRST_LENGTH);

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void starter(MainActivity activity) throws IOException {
        //建立通道
        ServerSocketChannel socketChannel  = ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(SocketConstantConfig.CHANNEL_PORT));
        while (SocketConstantConfig.IS_FLAG){
            String s = IpAddress.getIpAddress();
            //等待连接
            activity.runOnUiThread(()->{
                //获取本机局域网ip地址
                Toast.makeText(activity, "ip地址:"+s, Toast.LENGTH_LONG).show();
            });
            SocketChannel accept = socketChannel.accept();
            channelProcess(accept);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void channelProcess(SocketChannel client) throws IOException {
        while (SocketConstantConfig.IS_FLAG){
            //等待读取
            client.read(metaData);
            byte[] array = getMetaDataBytes(metaData);
            //获取携带数据长度
            ByteBuffer byteBuffer = ByteBuffer.allocate(array[0] << 8 | array[1]);
            Optional.ofNullable(OPTBHandlerChache.getHandler(array[2])).orElse(new OPTBErrorHandler()).execute(getMetaDataBytes(byteBuffer),activity);
        }
    }
    private byte[] getMetaDataBytes(ByteBuffer byteBuffer){
        byteBuffer.flip();
        byte[] array = byteBuffer.array();
        byteBuffer.clear();
        return array;
    }

}

