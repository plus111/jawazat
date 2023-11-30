package com.qss.testtwospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Locale;

public class Error_Activity extends AppCompatActivity {

    private Button back_btn;
    private LinearLayout err_speech;
    private LinearLayout network_err;
    private LinearLayout mic;
    private LinearLayout mic2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_error);

        back_btn = findViewById(R.id.back_btn);
        err_speech = findViewById(R.id.err_speech);
        network_err = findViewById(R.id.network_err);
        mic = findViewById(R.id.error_mic1);
        mic2 = findViewById(R.id.network_mic2);


        // Retrieve the error message from the intent error_message
        String errorMessage = getIntent().getStringExtra("error_message");
        Log.d("errorMessage", "errorMessage: " + errorMessage);

        if (errorMessage.equals("Speech Timeout Error")){

            // Display the error message in a TextView or handle it as needed
            err_speech.setVisibility(View.VISIBLE);
            network_err.setVisibility(View.INVISIBLE);

        } else if (errorMessage.equals("Network Error")) {
            // Display the error message in a TextView or handle it as needed
            err_speech.setVisibility(View.INVISIBLE);
            network_err.setVisibility(View.VISIBLE);

        }

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Error_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Error_Activity.this, Push_TalkActivity.class);

                Context ct = Error_Activity.this;
                Locale locale =getResources().getConfiguration().locale;
                String languageCode = locale.getLanguage();
                Log.i("TAG", languageCode);
                intent.putExtra("pushtalk","pushtalk");
                if (languageCode.equals("en")){

                    intent.putExtra("langg","en-US");
                    intent.putExtra("textlang","en");

                }else if (languageCode.equals("ar")) {


                    intent.putExtra("langg","ar-SA");
                    intent.putExtra("textlang","ar");

                }else if (languageCode.equals("fr")){

                    intent.putExtra("langg","fr-FR");
                    intent.putExtra("textlang","fr");

                }else if (languageCode.equals("zh")){

                    intent.putExtra("langg","zh-tw");
                    intent.putExtra("textlang","zh");

                }
                startActivity(intent);
                finish();
            }
        });
        mic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Error_Activity.this, Push_TalkActivity.class);

                Context ct = Error_Activity.this;
                Locale locale =getResources().getConfiguration().locale;
                String languageCode = locale.getLanguage();
                Log.i("TAG", languageCode);
                intent.putExtra("pushtalk","pushtalk");
                if (languageCode.equals("en")){

                    intent.putExtra("langg","en-US");
                    intent.putExtra("textlang","en");

                }else if (languageCode.equals("ar")) {


                    intent.putExtra("langg","ar-SA");
                    intent.putExtra("textlang","ar");

                }else if (languageCode.equals("fr")){

                    intent.putExtra("langg","fr-FR");
                    intent.putExtra("textlang","fr");

                }else if (languageCode.equals("zh")){

                    intent.putExtra("langg","zh-tw");
                    intent.putExtra("textlang","zh");

                }
                startActivity(intent);
                finish();
            }
        });


    }
}