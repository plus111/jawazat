package com.qss.testtwospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {


    private Context ct ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


}

//
//
//    public LanguageManager(Context ctx){
//
//        ct = ctx;
//    }
//    public void updateResource(){
//        Locale locale = Locale.getDefault();
//        String languageCode = locale.getLanguage();
//        Log.i("TAG", languageCode);
//        if (languageCode.equals("en")){
//            locale = new Locale("ar");
////            Configuration configuration = ct.getResources().getConfiguration();
////            configuration.setLayoutDirection(locale);
////            ct.getResources().updateConfiguration(configuration, null);
//        }else {
//            locale = new Locale("en");
////            Configuration configuration = ct.getResources().getConfiguration();
////            configuration.setLayoutDirection(locale);
////            ct.getResources().updateConfiguration(configuration, null);
//        }
//        locale.setDefault(locale);
//        Resources res = ct.getResources();
//        Configuration config = res.getConfiguration();
//        config.locale = locale;
//        res.updateConfiguration(config,res.getDisplayMetrics());
//
//    }