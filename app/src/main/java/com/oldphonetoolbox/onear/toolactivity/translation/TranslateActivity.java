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

import java.util.LinkedList;
import java.util.List;

public class TranslateActivity extends OPTBActivityCompat {

    private TextView chn_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String source = getIntent().getStringExtra("english");
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
    protected RelativeLayout getMainLayout() {
        return findViewById(R.id.transition_main);
    }

    @Override
    protected List<TextView> getTextView() {
        List<TextView> list = new LinkedList<>();
        list.add(findViewById(R.id.ts_ch));
        list.add(findViewById(R.id.ts_en));
        return list;
    }


    @Override
    public void close() {
    }

    public void setText(String text){
        this.chn_view.setText(text);
    }
}