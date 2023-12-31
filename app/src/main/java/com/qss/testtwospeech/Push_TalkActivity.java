package com.qss.testtwospeech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;
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
    private ImageView load;
    private TextView press_text;
    private String textlang;
    private LinearLayout listen_lin;
    private LinearLayout speak_lin;
    public int errorCode;
    Handler handler = new Handler();
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
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
        load = findViewById(R.id.load);
        headphone = findViewById(R.id.headphone_speak);

        //linear layout
        listen_lin = findViewById(R.id.listen_lin);
        speak_lin = findViewById(R.id.speak_lin);

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
        Log.d("selectedLanguage", "selectedLanguage: " + selectedLanguage);

        textlang = Objects.requireNonNull(getIntent().getExtras()).getString("textlang");
        Log.d("textlang", "textlang: " + textlang);
        textChange(textlang);


        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, selectedLanguage);
        Log.d("speechRecognizerIntent", "speechRecognizerIntent: " + speechRecognizerIntent);



        if (getIntent().getExtras().getString("pushtalk").equals("pushtalk")){

            iv_mic.setEnabled(true);
//            mic.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    listen_lin.setVisibility(View.VISIBLE);
//                    mic.setVisibility(View.INVISIBLE);
//                    press_text.setVisibility(View.INVISIBLE);
//
//                    speechRecognizer.setRecognitionListener(new RecognitionListener() {
//                        @Override
//                        public void onReadyForSpeech(Bundle bundle) {
//                        }
//
//                        @Override
//                        public void onBeginningOfSpeech() {
//                        }
//
//                        @Override
//                        public void onRmsChanged(float v) {
//                        }
//
//                        @Override
//                        public void onBufferReceived(byte[] bytes) {
//                        }
//
//                        @Override
//                        public void onEndOfSpeech() {
//                            load.setVisibility(View.VISIBLE);
//                            listen_lin.setVisibility(View.INVISIBLE);
//                        }
//
//                        @Override
//                        public void onError(int errorCode) {
//                            handleSpeechRecognizerError(errorCode);
//                        }
//
//                        @Override
//                        public void onResults(Bundle bundle) {
////                            iv_mic.setImageResource(R.drawable.mic_button);
//
//                            ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
//
//                            if (data != null && !data.isEmpty()) {
//                                // Recognized speech is not empty
//                                tv_Speech_to_text.setText(data.get(0));
//                                sendingAns(String.valueOf(data.get(0)), selectedLanguage);
//                                load.setVisibility(View.INVISIBLE);
//                                iv_mic.setVisibility(View.VISIBLE);
//
//                            } else if (data == null || data.isEmpty()) {
//                                // Handle the scenario when there is no recognized speech
////                                handleSpeechRecognizerError(errorCode);
//
//                            } else {
//                                // Handle other scenarios, such as the network error
//                                if (errorCode == SpeechRecognizer.ERROR_NETWORK) {
//                                    // Handle the network error
//                                }
//                            }
//
//                        }
//
//
//
//
//                        @Override
//                        public void onPartialResults(Bundle bundle) {
//
//                        }
//
//                        @Override
//                        public void onEvent(int i, Bundle bundle) {
//
//                        }
//                    });
//                }
//            });

            timerbtn(textlang);
        }else {

            timerbtn(textlang);
        }



//        mic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mic.setVisibility(View.INVISIBLE);
//                press_text.setVisibility(View.INVISIBLE);
//                listen_lin.setVisibility(View.VISIBLE);
//
//                speechRecognizer.setRecognitionListener(new RecognitionListener() {
//                    @Override
//                    public void onReadyForSpeech(Bundle bundle) {
//                    }
//
//                    @Override
//                    public void onBeginningOfSpeech() {
//                    }
//
//                    @Override
//                    public void onRmsChanged(float v) {
//                    }
//
//                    @Override
//                    public void onBufferReceived(byte[] bytes) {
//                    }
//
//                    @Override
//                    public void onEndOfSpeech() {
//                    }
//
//                    @Override
//                    public void onError(int errorCode) {
//                        if (errorCode == SpeechRecognizer.ERROR_SPEECH_TIMEOUT) {
//                            handleSpeechRecognizerError(errorCode);
//                        } else if (errorCode == SpeechRecognizer.ERROR_NETWORK) {
//                            handleSpeechRecognizerError(errorCode);
//                        }
//                    }
//
//                    @Override
//                    public void onResults(Bundle bundle) {
//                        iv_mic.setImageResource(R.drawable.mic_button);
//                        ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
//                        tv_Speech_to_text.setText(data.get(0));
//
//                        //calling the functions
//                        sendingAns(String.valueOf(data.get(0)), selectedLanguage);
//
//                    }
//
//                    @Override
//                    public void onPartialResults(Bundle bundle) {
//
//                    }
//
//                    @Override
//                    public void onEvent(int i, Bundle bundle) {
//
//                    }
//                });
//            }
//        });

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
//                    iv_mic.setImageResource(R.drawable.mic_button);
                    speechRecognizer.startListening(speechRecognizerIntent);
                }
                return false;
            }
        });

        micTimer();
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

        String url = "http://conversation.qltyss.com/sentence";
        RequestQueue queue = Volley.newRequestQueue(Push_TalkActivity.this);

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            mic.setVisibility(View.INVISIBLE);
                            listen_lin.setVisibility(View.INVISIBLE);
                            speak_lin.setVisibility(View.VISIBLE);

                            answerText.setText(response);
                            Log.d("SuccessResponse", "onResponse: ");
                            // Delayed visibility change after 3 seconds
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Set visibility back to invisible after 10 seconds
                                    mic.setVisibility(View.VISIBLE);
                                    iv_mic.setVisibility(View.VISIBLE);
                                    press_text.setVisibility(View.VISIBLE);
                                    speak_lin.setVisibility(View.INVISIBLE);
                                    listen_lin.setVisibility(View.INVISIBLE);
                                    load.setVisibility(View.INVISIBLE);

                                }
                            }, 15000); //  10 seconds


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
            // Set the retry policy
        request.setRetryPolicy(new DefaultRetryPolicy(
                    0,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);

    }

    public void textChange(String textlang){
        Configuration configuration = getResources().getConfiguration();
        Locale newLocale;
        switch (textlang) {
            case "en":
                newLocale = new Locale("en");
                Log.d("Locale", "Locale: "+ newLocale);
                Log.d("textlang", "textlang: " + textlang);
                break;

            case "ar":
                newLocale = new Locale("ar");
                Log.d("Locale", "Locale: "+ newLocale);
                Log.d("textlang", "textlang: " + textlang);
                break;

            case "fr":
                newLocale = new Locale("fr");
                Log.d("Locale", "Locale: "+ newLocale);
                Log.d("textlang", "textlang: " + textlang);
                break;

            case "zh":
                newLocale = new Locale("zh");
                Log.d("Locale", "Locale: "+ newLocale);
                Log.d("textlang", "textlang: " + textlang);
                break;

            default:
                Log.d("else statment", "not entering the if statement ");
                return;
        }
        configuration.setLocale(newLocale);
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        updateLayout();

    }
    private void updateLayout() {
        press_text.setText(getResources().getString(R.string.text));
        listen_textt.setText(getResources().getString(R.string.mic_textt));
        speak_textt.setText(getResources().getString(R.string.ans_textt));
    }

    public void timerbtn(String textlang){

        if (textlang.equals("en")) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    iv_mic.setEnabled(true);
                    mic.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listen_lin.setVisibility(View.VISIBLE);
                            mic.setVisibility(View.INVISIBLE);
                            press_text.setVisibility(View.INVISIBLE);

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
                                    load.setVisibility(View.VISIBLE);
                                    listen_lin.setVisibility(View.INVISIBLE);
                                }

                                @Override
                                public void onError(int errorCode) {

                                    Log.d("handleSpeechRecognizer", "handleSpeechRecognizer: " + errorCode);
                                    handleSpeechRecognizerError(errorCode);
                                }

                                @Override
                                public void onResults(Bundle bundle) {
//                                    iv_mic.setImageResource(R.drawable.mic_button);
                                    ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                                    if (data != null && !data.isEmpty()) {
                                        // Recognized speech is not empty
                                        tv_Speech_to_text.setText(data.get(0));
                                        sendingAns(String.valueOf(data.get(0)), selectedLanguage);
                                        load.setVisibility(View.INVISIBLE);


                                    } else if (data == null || data.isEmpty()) {
                                        // Handle the scenario when there is no recognized speech
//                                        handleSpeechRecognizerError(errorCode);

                                    } else {
                                        // Handle other scenarios, such as the network error
                                        if (errorCode == SpeechRecognizer.ERROR_NETWORK) {
                                            // Handle the network error
                                        }
                                    }

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
                }
            }, 9000);

            iv_mic.setEnabled(false);



        } else if (textlang.equals("ar")) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    iv_mic.setEnabled(true);
                    mic.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mic.setVisibility(View.INVISIBLE);
                            press_text.setVisibility(View.INVISIBLE);
                            listen_lin.setVisibility(View.VISIBLE);

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
                                    load.setVisibility(View.VISIBLE);
                                    listen_lin.setVisibility(View.INVISIBLE);
                                }

                                @Override
                                public void onError(int errorCode) {
                                    handleSpeechRecognizerError(errorCode);
                                }

                                @Override
                                public void onResults(Bundle bundle) {
//                                    iv_mic.setImageResource(R.drawable.mic_button);

                                    ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                                    if (data != null && !data.isEmpty()) {
                                        // Recognized speech is not empty
                                        tv_Speech_to_text.setText(data.get(0));
                                        sendingAns(String.valueOf(data.get(0)), selectedLanguage);
                                        load.setVisibility(View.INVISIBLE);
                                        iv_mic.setVisibility(View.VISIBLE);

                                    } else if (data == null || data.isEmpty()) {
                                        // Handle the scenario when there is no recognized speech
//                                        handleSpeechRecognizerError(errorCode);

                                    } else {
                                        // Handle other scenarios, such as the network error
                                        if (errorCode == SpeechRecognizer.ERROR_NETWORK) {
                                            // Handle the network error
                                        }
                                    }

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
                }
            }, 10000);

            iv_mic.setEnabled(false);


        } else if (textlang.equals("fr")) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    iv_mic.setEnabled(true);
                    mic.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mic.setVisibility(View.INVISIBLE);
                            press_text.setVisibility(View.INVISIBLE);
                            listen_lin.setVisibility(View.VISIBLE);

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
                                    load.setVisibility(View.VISIBLE);
                                    listen_lin.setVisibility(View.INVISIBLE);
                                }

                                @Override
                                public void onError(int errorCode) {
                                    handleSpeechRecognizerError(errorCode);
                                }

                                @Override
                                public void onResults(Bundle bundle) {
//                                    iv_mic.setImageResource(R.drawable.mic_button);

                                    ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                                    if (data != null && !data.isEmpty()) {
                                        // Recognized speech is not empty
                                        tv_Speech_to_text.setText(data.get(0));
                                        sendingAns(String.valueOf(data.get(0)), selectedLanguage);
                                        load.setVisibility(View.INVISIBLE);
                                        iv_mic.setVisibility(View.VISIBLE);

                                    } else if (data == null || data.isEmpty()) {
                                        // Handle the scenario when there is no recognized speech
//                                        handleSpeechRecognizerError(errorCode);

                                    } else {
                                        // Handle other scenarios, such as the network error
                                        if (errorCode == SpeechRecognizer.ERROR_NETWORK) {
                                            // Handle the network error
                                        }
                                    }

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
                }
            }, 11000);

            iv_mic.setEnabled(false);

        } else if (textlang.equals("zh")) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    iv_mic.setEnabled(true);
                    mic.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mic.setVisibility(View.INVISIBLE);
                            press_text.setVisibility(View.INVISIBLE);
                            listen_lin.setVisibility(View.VISIBLE);

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
                                    load.setVisibility(View.VISIBLE);
                                    listen_lin.setVisibility(View.INVISIBLE);
                                }

                                @Override
                                public void onError(int errorCode) {
                                    Log.d( "handleSpeechRecognizer", " handleSpeechRecognizer: " +  errorCode);
                                    handleSpeechRecognizerError(errorCode);
                                }

                                @Override
                                public void onResults(Bundle bundle) {
//                                    iv_mic.setImageResource(R.drawable.mic_button);

                                    ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                                    if (data != null && !data.isEmpty()) {
                                        // Recognized speech is not empty
                                        tv_Speech_to_text.setText(data.get(0));
                                        sendingAns(String.valueOf(data.get(0)), selectedLanguage);
                                        load.setVisibility(View.INVISIBLE);
                                        iv_mic.setVisibility(View.VISIBLE);

                                    } else if (data == null || data.isEmpty()) {
                                        // Handle the scenario when there is no recognized speech
//                                        handleSpeechRecognizerError(errorCode);

                                    } else {
                                        // Handle other scenarios, such as the network error
                                        if (errorCode == SpeechRecognizer.ERROR_NETWORK) {
//                                            handleSpeechRecognizerError(errorCode);
                                        }
                                    }

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
                }
            }, 12000);
            iv_mic.setEnabled(false);
        }
    }

    private void handleSpeechRecognizerError(int errorCode) {
        String errorMessage = " ";
        if (errorCode == SpeechRecognizer.ERROR_NO_MATCH  && errorCode == SpeechRecognizer.ERROR_SPEECH_TIMEOUT) {
            errorMessage = "Speech Timeout Error ";
        } else if (errorCode == SpeechRecognizer.ERROR_NETWORK) {
            errorMessage = "Network Error";
        } else {

        }


        Intent intent = new Intent(Push_TalkActivity.this, Error_Activity.class);
        intent.putExtra("error_message", errorMessage);
        finish();
        startActivity(intent);

    }

    public void micTimer(){
        // url to post our data
        String url = "http://conversation.qltyss.com/sentence";
        RequestQueue queue = Volley.newRequestQueue(Push_TalkActivity.this);

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle response
                        Log.d("TAG", "onResponse: ");
                        // Set visibility back to invisible after 15 seconds
                        mic.setVisibility(View.VISIBLE);
                        iv_mic.setVisibility(View.VISIBLE);
                        press_text.setVisibility(View.VISIBLE);
                        speak_lin.setVisibility(View.INVISIBLE);
                        listen_lin.setVisibility(View.INVISIBLE);
                        load.setVisibility(View.INVISIBLE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Log.e("Error", "onErrorResponse: ", error);
                    }
                });

        queue.add(request);
    }
}