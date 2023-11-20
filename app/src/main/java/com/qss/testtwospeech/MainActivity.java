package com.qss.testtwospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
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
import android.preference.PreferenceManager;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    Button Englishbtn;
    Button Arabicbtn;
    Button Frenchbtn;
    Button Chinesebtn;
    private SpeechRecognizer speechRecognizer;

    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";
    final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Englishbtn = findViewById(R.id.lang_englishbtn);
        Arabicbtn = findViewById(R.id.lang_Arabicbtn);
        Frenchbtn = findViewById(R.id.lang_Frenchbtn);
        Chinesebtn = findViewById(R.id.lang_chinesebtn);


        Englishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                lang.updateResource("en");
//                recreate();


                // url to post our data
                String url = "http://192.168.100.67:5000/sentence";
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                // Move to another page using Intent
                Intent intent = new Intent(MainActivity.this, Listen.class);
                intent.putExtra("langg","en-US");
                startActivity(intent);

                StringRequest request = new StringRequest(
                        Request.Method.POST,
                        url,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Handle response
                                Log.d("TAG", "onResponse: ");
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
                                    jsonBody.put("lang", "en-US");
                                    jsonBody.put("sentence", "welcoming");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                return jsonBody.toString().getBytes();
                            }
                };

                queue.add(request);

            }

        });
        Arabicbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // url to post our data
                String url = "http://192.168.100.67:5000/sentence";
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                // Move to another page using Intent
                Intent intent = new Intent(MainActivity.this, Listen.class);
                intent.putExtra("langg","ar-SA");
                startActivity(intent);
                StringRequest request = new StringRequest(
                        Request.Method.POST,
                        url,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Handle response
                                Log.d("TAG", "onResponse: ");
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
                            jsonBody.put("lang", "ar-SA");
                            jsonBody.put("sentence", "welcoming");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return jsonBody.toString().getBytes();
                    }
                };

                queue.add(request);

            }
        });
        Frenchbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            lang.updateResource("fr");
//            recreate();


            // url to post our data
            String url = "http://192.168.100.67:5000/sentence";
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

            // Move to another page using Intent
            Intent intent = new Intent(MainActivity.this, Listen.class);
            intent.putExtra("langg","fr-FR");
            startActivity(intent);

            StringRequest request = new StringRequest(
                    Request.Method.POST,
                    url,
                    new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Handle response
                            Log.d("TAG", "onResponse: ");
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
                        jsonBody.put("lang", "fr-FR");
                        jsonBody.put("sentence", "welcoming");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return jsonBody.toString().getBytes();
                }
            };

            queue.add(request);


            }
        });
        Chinesebtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            lang.updateResource("zh");
//            recreate();

            // url to post our data
            String url = "http://192.168.100.67:5000/sentence";
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

            // Move to another page using Intent
            Intent intent = new Intent(MainActivity.this, Listen.class);
            intent.putExtra("langg","zh-tw");
            startActivity(intent);

            StringRequest request = new StringRequest(
                    Request.Method.POST,
                    url,
                    new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Handle response
                            Log.d("TAG", "onResponse: ");
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
                        jsonBody.put("lang", "zh-tw");
                        jsonBody.put("sentence", "welcoming");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return jsonBody.toString().getBytes();
                }
            };

            queue.add(request);

            }

        });
    }
}


