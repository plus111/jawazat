package com.qss.testtwospeech;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;

import java.util.Locale;


public class LanguageManager {

    private Context ct;

    @SuppressLint("NotConstructor")
    public LanguageManager(Context ctx){
        ct = ctx;
    }
    public void updateResource(String code) {

        Locale locale = new Locale(code);
        locale.setDefault(locale);
        Resources res = ct.getResources();
        Configuration config = res.getConfiguration();
        config.locale = locale;
        res.updateConfiguration(config,res.getDisplayMetrics());
    }
}
