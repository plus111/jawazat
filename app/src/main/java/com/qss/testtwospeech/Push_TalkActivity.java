package com.qss.testtwospeech;

import androidx.annotation.NonNull;
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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class Push_TalkActivity extends AppCompatActivity {

    private ImageView iv_mic;
    private TextView tv_Speech_to_text;
    private TextView answerText;
    private TextView listen_textt;
    private TextView speak_textt;
    private SpeechRecognizer speechRecognizer;
    private Button back_btn;
    private RelativeLayout mic;
    private ImageView headphone;
    private ImageView speak;
    private TextView press_text;

    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    LanguageManager lang = new LanguageManager(this);
    String selectedLanguage;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.push_talk);

        iv_mic = findViewById(R.id.mic_btn);
        press_text = findViewById(R.id.press_text);
        tv_Speech_to_text = findViewById(R.id.tv_speech_to_text);
        answerText = findViewById(R.id.text_res);
        back_btn = findViewById(R.id.back_btn);
        mic = findViewById(R.id.mic);
        headphone = findViewById(R.id.headphone);
        speak = findViewById(R.id.speak);
        listen_textt = findViewById(R.id.listen_textt);
        speak_textt = findViewById(R.id.speak_textt);
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);



        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }



        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);


        selectedLanguage = Objects.requireNonNull(getIntent().getExtras()).getString("langg");
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, selectedLanguage);
        Log.d("speechRecognizerIntent", "speechRecognizerIntent: " + speechRecognizerIntent);


        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                lang.updateResource("en");
//                recreate();
                mic.setVisibility(View.INVISIBLE);
                press_text.setVisibility(View.INVISIBLE);
                headphone.setVisibility(View.VISIBLE);
                listen_textt.setVisibility(View.VISIBLE);
                tv_Speech_to_text.setVisibility(View.VISIBLE);
                speechRecognizer.setRecognitionListener(new RecognitionListener() {
                    @Override
                    public void onReadyForSpeech(Bundle bundle) {
                    }

                    @Override
                    public void onBeginningOfSpeech() {

                    }

                    @Override
                    public void onRmsChanged(float v) {
                    }

                    @Override
                    public void onBufferReceived(byte[] bytes) {
                    }

                    @Override
                    public void onEndOfSpeech() {
                    }

                    @Override
                    public void onError(int i) {
                    }

                    @Override
                    public void onResults(Bundle bundle) {
                        iv_mic.setImageResource(R.drawable.mic_button);
                        ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                        tv_Speech_to_text.setText(data.get(0));

                        //calling the functions
                        sendingAns(String.valueOf(data.get(0)), selectedLanguage);
                    }

                    @Override
                    public void onPartialResults(Bundle bundle) {

                    }

                    @Override
                    public void onEvent(int i, Bundle bundle) {

                    }
                });
            }
        });

//        sendingAns(String.valueOf(tv_Speech_to_text),selectedLanguage);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Push_TalkActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



        iv_mic.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    speechRecognizer.stopListening();

                }
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    iv_mic.setImageResource(R.drawable.mic_button);
                    speechRecognizer.startListening(speechRecognizerIntent);
                }
                return false;
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        speechRecognizer.destroy();
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.RECORD_AUDIO}, REQUEST_CODE_SPEECH_INPUT);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }

    }


    public void sendingAns(String tv_Speech_to_text,String selectedLanguage){

        String url = "http://192.168.100.67:5000/sentence";
        RequestQueue queue = Volley.newRequestQueue(Push_TalkActivity.this);

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            headphone.setVisibility(View.INVISIBLE);
                            listen_textt.setVisibility(View.INVISIBLE);

                            speak.setVisibility(View.VISIBLE);
                            speak_textt.setVisibility(View.VISIBLE);
                            answerText.setVisibility(View.VISIBLE);
                            answerText.setText(response);
                            Log.d("SuccessResponse", "onResponse: ");


                        } catch (Exception e) {
                            Log.e("Error", "onErrorResponse: ", e);
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Log.e("Error", "onErrorResponse: ", error);
                    }
                }) {
            @Override
            public String getBodyContentType() {
                Log.d("TAG", "getBodyContentType: ");
                return "application/json";
            }

            @Override
            public byte[] getBody() {
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("lang", selectedLanguage);
                    Log.d("chosenlang", "getBody: ");
                    jsonBody.put("sentence", "" + tv_Speech_to_text);
                    Log.d("sentsentence", "sentence: ");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return jsonBody.toString().getBytes();
            }
        };

        queue.add(request);

    }
}