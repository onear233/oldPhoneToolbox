package com.oldphonetoolbox.onear;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SettingActivity extends AppCompatActivity {
    //数据源，tittleArray为标题，descriptionArray为描述
    private static final int[] tittleArray = {R.string.settings_color_tittle,};
    private static final int[] descriptionArray = {R.string.settings_color_desc};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        showListview();
    }

    private void showListview() {
        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 0; i < tittleArray.length; i++) {}
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,list);
    }
}