package com.oldphonetoolbox.onear;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SettingActivity extends AppCompatActivity {
    //数据源，tittleArray为标题，descriptionArray为描述
    private final String[] titleArray = {getResources().getString(R.string.settings_color_title),getResources().getString(R.string.settings_st2_desc)};
    private final String[] descriptionArray = {getResources().getString(R.string.settings_color_desc),getResources().getString(R.string.settings_st2_desc)};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        showListview();
    }

    private void showListview() {
        List<HashMap<String, String>> list = new ArrayList<>();
        for (int i = 0; i < titleArray.length; i++) {
            HashMap <String,String> item = new HashMap<>();
            item.put("tittle",titleArray[i]);
            item.put("desc",descriptionArray[i]);
            list.add(item);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,list,R.layout.simple_list_item,new String[]{"tittle","desc"},new int[]{R.id.setting_item_tittle,R.id.setting_item_desc});
        ListView lv_settings = findViewById(R.id.lv_settings);
        lv_settings.setAdapter(simpleAdapter);
        //dsahfashfa
    }
}