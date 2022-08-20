package com.oldphonetoolbox.onear.toolactivity.picture;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.oldphonetoolbox.onear.R;
import com.oldphonetoolbox.onear.toolactivity.OPTBActivityCompat;


public class PictureDisplay extends OPTBActivityCompat {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_picture_display);
        ((ImageView)findViewById(R.id.picture)).setImageBitmap(byteArrayToBitmap(getIntent().getByteArrayExtra("photo")));
    }

    private Bitmap byteArrayToBitmap(byte[] source) {
        return BitmapFactory.decodeByteArray(source, 0, source.length);
    }
    @Override
    public void close() {}
}