package com.oldphonetoolbox.onear.toolactivity.monitor;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.oldphonetoolbox.onear.R;
import com.oldphonetoolbox.onear.data.pojo.WindowsBean;
import com.oldphonetoolbox.onear.toolactivity.OPTBActivityCompat;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class MonitorActivityCompat extends OPTBActivityCompat {
    TextView title;
    TextView cpuUsageAndCount;
    TextView memoryUsageAndTotal;
    TextView processNum;
    public ServerSocketChannel serverSocketChannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_connected);
        title = setLight(findViewById(R.id.window_title));
        cpuUsageAndCount = setLight(findViewById(R.id.cpu_usage_count));
        memoryUsageAndTotal = setLight(findViewById(R.id.memory_usage_total));
        processNum = setLight(findViewById(R.id.process_num));
        new Thread(new MonitorThread(this)).start();
    }

    public void setData(WindowsBean data){
        System.out.println(data.toString());
        title.setText(data.getWindowTitle());
        cpuUsageAndCount.setText("CPU使用率"+data.getCpuUsage()+"/ CPU核心数"+data.getCpuCount());
        memoryUsageAndTotal.setText("内存使用率"+data.getMemoryUsage()+"/ 内存总量"+data.getMemoryTotal());
        processNum.setText("进程数:"+data.getNumberOfProcesses());
    }

    @Override
    public void close() {
        MonitorThread.isRunning = false;
    }
    private TextView setLight(TextView textView) {
        //设置荧光效果
        textView.setShadowLayer(30,0,0, Color.WHITE);
        return textView;
    }
}