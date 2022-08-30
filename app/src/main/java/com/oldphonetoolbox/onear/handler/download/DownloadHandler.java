package com.oldphonetoolbox.onear.handler.download;

import android.content.Intent;
import android.util.Log;

import com.oldphonetoolbox.frame.annotation.Handler;
import com.oldphonetoolbox.onear.MainActivity;
import com.oldphonetoolbox.onear.handler.OPTBHandlerAbstract;
import com.oldphonetoolbox.onear.toolactivity.download.DownloadProcess;
import com.oldphonetoolbox.onear.toolactivity.download.DownloaderThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Handler(0x03)
public class DownloadHandler extends OPTBHandlerAbstract {
    //线程池
    public static final ExecutorService executorService = Executors.newFixedThreadPool(3);

    @Override
    protected void executeCode(byte[] data, MainActivity activity) {
        if(data!=null&&data.length!=0){
            String url = new String(data);
            Log.i(DownloadHandler.class.getName(), "挂载链接："+url);
            executorService.execute(new DownloaderThread(url.substring(url.lastIndexOf("/")+1),url,activity));
        }
        Intent intent = new Intent(activity, DownloadProcess.class);
        activity.startActivity(intent);
    }
}
