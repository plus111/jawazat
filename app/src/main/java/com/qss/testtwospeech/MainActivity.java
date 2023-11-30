package com.qss.testtwospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    Button quit_btn;
    Button Englishbtn;
    Button Arabicbtn;
    Button Frenchbtn;
    Button Chinesebtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quit_btn = findViewById(R.id.quit_bttn );
        Englishbtn = findViewById(R.id.lang_englishbtn);
        Arabicbtn = findViewById(R.id.lang_Arabicbtn);
        Frenchbtn = findViewById(R.id.lang_Frenchbtn);
        Chinesebtn = findViewById(R.id.lang_chinesebtn);


        quit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to close the app
                finish();
                System.exit(0);

            }
        });
        Englishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // url to post our data
                String url = "http://192.168.100.67:5000/sentence";
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                // Move to another page using Intent
                Intent intent = new Intent(MainActivity.this, Push_TalkActivity.class);
                intent.putExtra("langg","en-US");
                intent.putExtra("textlang","en");
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
                finish();
            }

        });
        Arabicbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // url to post our data
                String url = "http://192.168.100.67:5000/sentence";
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                // Move to another page using Intent
                Intent intent = new Intent(MainActivity.this, Push_TalkActivity.class);
                intent.putExtra("langg","ar-SA");
                intent.putExtra("textlang","ar");
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
                finish();
            }

        });
        Frenchbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // url to post our data
            String url = "http://192.168.100.67:5000/sentence";
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

            // Move to another page using Intent
            Intent intent = new Intent(MainActivity.this, Push_TalkActivity.class);
            intent.putExtra("langg","fr-FR");
            intent.putExtra("textlang","fr");

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
            finish();


            }
        });
        Chinesebtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            // url to post our data
            String url = "http://192.168.100.67:5000/sentence";
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

            // Move to another page using Intent
            Intent intent = new Intent(MainActivity.this, Push_TalkActivity.class);
            intent.putExtra("langg","zh-tw");
            intent.putExtra("textlang","zh");
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
            finish();

            }

        });
    }
}


