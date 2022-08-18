package com.oldphonetoolbox.onear.data.constant.socket;

public enum SocketHandlerConfig {
    HANDLER_TRANSLATION(0x00);
    private final byte id;
    SocketHandlerConfig(int id){
        this.id = (byte)id;
    }
    public byte getId(){
        return id;
    }
}
