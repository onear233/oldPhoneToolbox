package com.oldphonetoolbox.onear.toolactivity.monitor;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oldphonetoolbox.onear.R;
import com.oldphonetoolbox.onear.data.pojo.WindowsBean;
import com.oldphonetoolbox.onear.toolactivity.OPTBActivityCompat;

import java.nio.channels.ServerSocketChannel;
import java.util.LinkedList;
import java.util.List;

public class MonitorActivityCompat extends OPTBActivityCompat {
    TextView title;
    TextView cpuUsageAndCount;
    TextView memoryUsageAndTotal;
    TextView processNum;
    public ServerSocketChannel serverSocketChannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_connected);
        title = (findViewById(R.id.window_title));
        cpuUsageAndCount = (findViewById(R.id.cpu_usage_count));
        memoryUsageAndTotal = (findViewById(R.id.memory_usage_total));
        processNum = (findViewById(R.id.process_num));
        Log.i(MonitorActivityCompat.class.getName(), "进入监控界面等待数据");
        new Thread(new MonitorThread(this)).start();
    }

    @Override
    protected RelativeLayout getMainLayout() {
        return findViewById(R.id.monitor_activity_main);
    }

    @Override
    protected List<TextView> getTextView() {
        List<TextView> list = new LinkedList<>();
        list.add(findViewById(R.id.window_title));
        list.add(findViewById(R.id.cpu_usage_count));
        list.add(findViewById(R.id.memory_usage_total));
        list.add(findViewById(R.id.process_num));
        return list;
    }

    @SuppressLint("SetTextI18n")
    public void setData(WindowsBean data){
        title.setText(data.getWindowTitle());
        cpuUsageAndCount.setText("CPU使用率"+data.getCpuUsage()+"/ CPU核心数"+data.getCpuCount());
        memoryUsageAndTotal.setText("内存使用率"+data.getMemoryUsage()+"/ 内存总量"+data.getMemoryTotal());
        processNum.setText("进程数:"+data.getNumberOfProcesses());
    }

    @Override
    public void close() {
        MonitorThread.isRunning = false;
    }
}