package com.oldphonetoolbox.onear.toolactivity.download;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.oldphonetoolbox.onear.MainActivity;
import com.oldphonetoolbox.onear.R;
import com.oldphonetoolbox.onear.handler.OPTBHandlerAbstract;
import com.oldphonetoolbox.onear.toolactivity.OPTBActivityCompat;

import java.util.concurrent.TimeUnit;

public class DownloadProcess extends OPTBActivityCompat {
    public volatile static boolean[] isDownloading = new boolean[]{false,false,false};
    public volatile static int[] progress = new int[]{0,0,0};
    public volatile static String[] name = new String[]{"","",""};

    ProgressBar downloadProgress1;
    ProgressBar downloadProgress2;
    ProgressBar downloadProgress3;

    TextView downloadName1;
    TextView downloadName2;
    TextView downloadName3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_download_process);
        //获取控件
        downloadProgress1 = findViewById(R.id.pb_download1);
        downloadProgress2 = findViewById(R.id.pb_download2);
        downloadProgress3 = findViewById(R.id.pb_download3);
        downloadName1 = findViewById(R.id.process_download1);
        downloadName2 = findViewById(R.id.process_download2);
        downloadName3 = findViewById(R.id.process_download3);
        //每秒刷新一次控件
        while(true){
            downloadProgress1.setProgress(progress[0]);
            downloadProgress2.setProgress(progress[1]);
            downloadProgress3.setProgress(progress[2]);
            downloadName1.setText(name[0]);
            downloadName2.setText(name[1]);
            downloadName3.setText(name[2]);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void close() {}

}