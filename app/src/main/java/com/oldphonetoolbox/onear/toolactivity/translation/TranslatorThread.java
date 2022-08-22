package com.oldphonetoolbox.onear.toolactivity.translation;

import android.util.Log;

import com.oldphonetoolbox.onear.net.translate.Translator;

public class TranslatorThread implements Runnable{
    private final TranslateActivity activity;
    private final String source;
    public TranslatorThread(TranslateActivity activity,String source) {
        this.activity = activity;
        this.source = source;
    }

    @Override
    public void run() {
        String en = null;
        try {
            en = Translator.translate("en", "zh-CN", source);
        } catch (Exception e) {
            Log.e("translate", "翻译失败，错误信息:"+e.getMessage());
        }
        String finalEn = en;
        Log.i(TranslateActivity.class.getName(), "翻译成功，结果为:"+en);
        activity.runOnUiThread(()->activity.setText(finalEn));
    }
}
