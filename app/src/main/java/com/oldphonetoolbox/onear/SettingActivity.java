package com.oldphonetoolbox.onear;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    //数据源，tittleArray为标题，descriptionArray为描述

    Constant constant = new Constant();
    private final String[] titleArray = {constant.settingsColorTitle,constant.settingsPasswordTitle};
    private final String[] descriptionArray = {constant.settingsColorDesc,constant.settingsPasswordDesc};
    private List<Map<String, Object>> list;
    private HashMap<String, Object> item;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        showListview();
    }

    private void showListview() {
        list = new ArrayList<>();
        for (int i = 0; i < titleArray.length; i++) {
            item = new HashMap<>();
            item.put("title",titleArray[i]);
            item.put("desc",descriptionArray[i]);
            list.add(item);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, list,R.layout.simple_list_item_tv_only,new String[]{"title","desc"},new int[]{R.id.setting_item_tittle,R.id.setting_item_desc});
        ListView lv_settings = findViewById(R.id.lv_settings);
        lv_settings.setAdapter(simpleAdapter);
        lv_settings.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                break;
            case 1:
                showPasswordEnterDialog();//显示对话框的方法
                break;
        }
    }

    private void showPasswordEnterDialog() {
        editText = new EditText(SettingActivity.this);
        AlertDialog.Builder inputDialog = new AlertDialog.Builder(SettingActivity.this);
        inputDialog.setTitle("请输入密码").setView(editText);
        inputDialog.setPositiveButton("确定", (dialog, which) -> {
            if (editText.getText().toString().length() != 0){
                savePassword(editText.getText().toString());
            }else{
                Toast.makeText(SettingActivity.this,"密码为空！",Toast.LENGTH_SHORT);
                return;
            }
        });
        inputDialog.setNegativeButton("取消", (dialog, which) -> { return; });
        AlertDialog dialog = inputDialog.create();
        dialog.show();//展示对话框
        getPassword();


    }

    private void getPassword() {
        SharedPreferences getPWD = getSharedPreferences("password", MODE_PRIVATE);
        String getPassword = getPWD.getString("pwd","");
        editText.setText(getPassword);
    }


    private void savePassword(String pwd) {
        SharedPreferences password = getSharedPreferences("password", MODE_PRIVATE);
        SharedPreferences.Editor editor = password.edit();
        editor.putString("pwd",pwd);
        editor.commit();
    }
}