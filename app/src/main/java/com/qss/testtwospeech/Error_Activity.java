package com.qss.testtwospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Error_Activity extends AppCompatActivity {

    private Button back_btn;
    private LinearLayout err_speech;
    private LinearLayout network_err;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_error);

        back_btn = findViewById(R.id.quit_bttn);
        err_speech = findViewById(R.id.err_speech);
        network_err = findViewById(R.id.network_err);


        // Retrieve the error message from the intent
        String errorMessage = getIntent().getStringExtra("error_message");
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


    }
}