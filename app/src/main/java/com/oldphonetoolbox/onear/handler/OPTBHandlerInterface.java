package com.oldphonetoolbox.onear.handler;

import com.oldphonetoolbox.onear.MainActivity;

/**
 * 功能处理器接口
 * @author midreamsheep
 * */
public interface OPTBHandlerInterface {
    /**
     * 执行功能
     * @param data 传递数据
     * @param activity 页面对象
     * */
    void execute(byte[] data, MainActivity activity);
}
