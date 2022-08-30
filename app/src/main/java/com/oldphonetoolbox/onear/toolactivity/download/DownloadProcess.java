package com.oldphonetoolbox.onear.toolactivity.download;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oldphonetoolbox.onear.R;
import com.oldphonetoolbox.onear.toolactivity.OPTBActivityCompat;

import java.util.concurrent.TimeUnit;

public class DownloadProcess extends OPTBActivityCompat {
    public volatile static boolean[] isDownloading = new boolean[]{false,false,false};
    public volatile static int[] progress = new int[]{0,0,0};
    public volatile static String[] name = new String[]{"","",""};

    public ProgressBar downloadProgress1;
    public ProgressBar downloadProgress2;
    public ProgressBar downloadProgress3;

    public TextView downloadName1;
    public TextView downloadName2;
    public TextView downloadName3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_download_process);
        Log.i("DOWNLOAD", "进入进度显示页面");
        //获取控件
        downloadProgress1 = findViewById(R.id.pb_download1);
        downloadProgress2 = findViewById(R.id.pb_download2);
        downloadProgress3 = findViewById(R.id.pb_download3);
        downloadName1 = findViewById(R.id.process_download1);
        downloadName2 = findViewById(R.id.process_download2);
        downloadName3 = findViewById(R.id.process_download3);
        //每秒刷新一次控件
        new Thread(()-> new PageThread(this).flush()).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //从SharedPreferences中读取配置的颜色
        SharedPreferences getBackgroundColor = getSharedPreferences("backgroundColor", MODE_PRIVATE);
        String backgroundColor = getBackgroundColor.getString("backgroundColor","");
        SharedPreferences getTextColor = getSharedPreferences("textColor", MODE_PRIVATE);
        String textColor = getTextColor.getString("textColor","");
        if (backgroundColor.length() != 0){
            RelativeLayout downMain = findViewById(R.id.download_process_main);
            downMain.setBackgroundColor(Color.parseColor(backgroundColor));//设置背景颜色
        }
        if (textColor.length() != 0){
            TextView processDownload1 = findViewById(R.id.process_download1);
            TextView processDownload2 = findViewById(R.id.process_download2);
            TextView processDownload3 = findViewById(R.id.process_download3);
            int textColorInt = Color.parseColor(textColor);
            processDownload1.setTextColor(textColorInt);
            processDownload2.setTextColor(textColorInt);
            processDownload3.setTextColor(textColorInt);
        }
    }

    @Override
    public void close() {
        PageThread.isRunning = false;
    }
    private static class PageThread{
        private final DownloadProcess activity;
        public static volatile boolean isRunning = true;
        public PageThread(DownloadProcess activity){
            this.activity = activity;
        }

        public void flush() {
            Log.i("DOWNLOAD", "开始刷新控件");
            isRunning = true;
            while (isRunning){
                activity.runOnUiThread(()-> {
                    activity.downloadProgress1.setProgress(progress[0]);
                    activity.downloadProgress2.setProgress(progress[1]);
                    activity.downloadProgress3.setProgress(progress[2]);
                    activity.downloadName1.setText(name[0]);
                    activity.downloadName2.setText(name[1]);
                    activity.downloadName3.setText(name[2]);
                });
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    
                }
            }
        }
    }
}