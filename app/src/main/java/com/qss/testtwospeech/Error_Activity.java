package com.qss.testtwospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

public class Error_Activity extends AppCompatActivity {

    private LinearLayout err_speech;
    private LinearLayout network_err;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_error);

        err_speech = findViewById(R.id.err_speech);
        network_err = findViewById(R.id.network_err);


    }
}