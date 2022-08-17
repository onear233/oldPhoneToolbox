package com.oldphonetoolbox.onear.data.constant.socket;

public class SocketConstantConfig {
    //nio端口号
    public static final int CHANNEL_PORT = 7751;
    //保持循环
    public static boolean IS_FLAG = true;
    //第一次传递的值长度
    /**
     * 前两个字节 携带数据长度
     * 第三个字节 处理器id
     * */
    public static final int FIRST_LENGTH = 3;

    //TAG
    public static final String SOCKET_TAG = "网络通讯";
}
