package com.oldphonetoolbox.onear.toolactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.oldphonetoolbox.onear.socket.SocketCoreController;


public class OPTBActivityCompat extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SocketCoreController.optbActivityCompat = this;
    }
    public void back(){
        finish();
    }
}
