package com.oldphonetoolbox.onear.toolactivity.translation;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oldphonetoolbox.onear.R;
import com.oldphonetoolbox.onear.toolactivity.OPTBActivityCompat;

public class TranslateActivity extends OPTBActivityCompat {

    private TextView chn_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String source = getIntent().getStringExtra("english");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_translate);
        TextView eng_view = findViewById(R.id.ts_en);
        chn_view = findViewById(R.id.ts_ch);
        eng_view.setText(source);
        Log.i(TranslateActivity.class.getName(), "进入翻译界面");
        //执行翻译程序
        new Thread(new TranslatorThread(this,source)).start();
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
            RelativeLayout main = findViewById(R.id.transition_main);
            main.setBackgroundColor(Color.parseColor(backgroundColor));//设置背景颜色
        }
        if (textColor.length() != 0){
            TextView zhCn = findViewById(R.id.ts_ch);
            TextView usEn = findViewById(R.id.ts_en);
            int textColorInt = Color.parseColor(textColor);
            zhCn.setTextColor(textColorInt);
            usEn.setTextColor(textColorInt);
        }
    }

    @Override
    public void close() {
    }

    public void setText(String text){
        this.chn_view.setText(text);
    }
}