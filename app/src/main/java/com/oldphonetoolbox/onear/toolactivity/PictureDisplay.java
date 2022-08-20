package com.oldphonetoolbox.onear.toolactivity;

import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.oldphonetoolbox.onear.R;


public class PictureDisplay extends OPTBActivityCompat {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        byte[] source = getIntent().getByteArrayExtra("photo");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_picture_display);
        showPicture();
    }

    private void showPicture() {

    }



}