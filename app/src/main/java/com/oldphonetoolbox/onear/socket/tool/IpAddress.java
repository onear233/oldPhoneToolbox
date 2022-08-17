package com.oldphonetoolbox.onear.socket.tool;

import android.util.Log;

import com.oldphonetoolbox.onear.data.constant.socket.SocketConstantConfig;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class IpAddress {
    public static String getIpAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = addresses.nextElement();
                    if (!(ip instanceof Inet4Address)) {
                        continue;
                    }
                    String address = ip.getHostAddress();
                    Log.i(SocketConstantConfig.SOCKET_TAG, "本机局域网ip: "+address);
                    return address;
                }
            }
        } catch (Exception e) {
            Log.e(SocketConstantConfig.SOCKET_TAG,"IP地址获取失败" + e.toString());
        }
        return "";
    }
}
