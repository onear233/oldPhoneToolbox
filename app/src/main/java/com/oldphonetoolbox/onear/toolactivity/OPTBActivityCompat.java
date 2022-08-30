package com.oldphonetoolbox.onear.toolactivity;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.oldphonetoolbox.onear.R;
import com.oldphonetoolbox.onear.socket.SocketCoreController;

import java.util.List;


public abstract class OPTBActivityCompat extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        SocketCoreController.optbActivityCompat = this;
    }
    public abstract void close();
    public void back(){
        close();
        finish();
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
            RelativeLayout main = getMainLayout();
            main.setBackgroundColor(Color.parseColor(backgroundColor));//设置背景颜色
        }
        if (textColor.length() != 0){
            int textColorInt = Color.parseColor(textColor);
            for (TextView view : getTextView()) {
                view.setTextColor(textColorInt);
            }
        }
    }

    protected abstract RelativeLayout getMainLayout();

    protected abstract List<TextView> getTextView();
}
