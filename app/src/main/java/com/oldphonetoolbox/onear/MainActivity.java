package com.oldphonetoolbox.onear;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

import com.oldphonetoolbox.onear.receiver.BatteryReceiver;
import com.oldphonetoolbox.onear.socket.SocketCoreController;
import com.oldphonetoolbox.onear.toolactivity.download.DownloadProcess;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @SuppressLint("StaticFieldLeak")
    private static TextView batteryValue;
    private BatteryReceiver batteryReceiver;

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //设置应用横屏并全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        batteryValue = findViewById(R.id.battery_remain);
        findViewById(R.id.btn_setting).setOnClickListener(this);
        Log.i(TAG, "主界面初始化成功");
        //开辟一个新线程
        Thread socket = new Thread(() -> {
            try {
                Log.i(TAG, "开启网络通信线程");
                new SocketCoreController().starter(this);
            } catch (Exception e) {
                Log.e("Socket", "网络通信: " + e.getMessage());
                System.exit(500);
            }
        });
        socket.setDaemon(true);
        socket.start();

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
            RelativeLayout main = findViewById(R.id.main_activity);
            main.setBackgroundColor(Color.parseColor(backgroundColor));//设置背景颜色
        }
        if (textColor.length() != 0){
            TextClock textClock = findViewById(R.id.text_clock);
            TextView batteryTextView = findViewById(R.id.battery_remain);
            TextClock textDate = findViewById(R.id.text_date);
            int textColorInt = Color.parseColor(textColor);
            textClock.setTextColor(textColorInt);
            batteryTextView.setTextColor(textColorInt);
            textDate.setTextColor(textColorInt);
        }



    }

    @Override
    protected void onStart() {
        super.onStart();
        batteryListener();
    }

    public void batteryListener() {
        batteryReceiver = new BatteryReceiver(); //实例化BatteryReceiver对象，并声明为全局
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED); //创建过滤器，只收这一个广播
        registerReceiver(batteryReceiver,intentFilter);
    }


    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(batteryReceiver);
    }

    @SuppressLint("SetTextI18n")
    public static void setBattery(int a) {
        Log.i(TAG, "setBattery: 当前电量为");
        batteryValue.setText("目前电量：" + a + "%");
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_setting){
            startActivity(new Intent(this,SettingActivity.class));
        }
    }

}

