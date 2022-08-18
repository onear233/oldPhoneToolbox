package com.oldphonetoolbox.onear.handler.translation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.oldphonetoolbox.onear.R;

public class TranslateActivity extends AppCompatActivity {


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

        TextView eng_view = setLight(findViewById(R.id.ts_en));
        TextView chn_view = setLight(findViewById(R.id.ts_ch));
        eng_view.setText(source);
        //执行翻译程序
    }

    private TextView setLight(TextView textView) {
        //设置荧光效果
        textView.setShadowLayer(12,0,0, Color.WHITE);
        return textView;
    }
}