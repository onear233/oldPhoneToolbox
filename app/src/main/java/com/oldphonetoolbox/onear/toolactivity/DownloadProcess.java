package com.oldphonetoolbox.onear.toolactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.oldphonetoolbox.onear.R;

public class DownloadProcess extends AppCompatActivity {

    private int downloadProcess1 = 11;
    private int downloadProcess2 = 45;
    private int downloadProcess3 = 14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_download_process);

        ProgressBar downloadProgress1 = findViewById(R.id.pb_download1);
        downloadProgress1.setProgress(downloadProcess1);

        ProgressBar downloadProgress2 = findViewById(R.id.pb_download2);
        downloadProgress2.setProgress(downloadProcess2);

        ProgressBar downloadProgress3 = findViewById(R.id.pb_download3);
        downloadProgress3.setProgress(downloadProcess3);
    }
}