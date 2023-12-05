package com.qss.testtwospeech;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private Button quit_btn;
    private Button Englishbtn;
    private Button Arabicbtn;
    private Button Frenchbtn;
    private Button Chinesebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quit_btn = findViewById(R.id.quit_bttn);
        Englishbtn = findViewById(R.id.lang_englishbtn);
        Arabicbtn = findViewById(R.id.lang_Arabicbtn);
        Frenchbtn = findViewById(R.id.lang_Frenchbtn);
        Chinesebtn = findViewById(R.id.lang_chinesebtn);

        quit_btn.setOnClickListener(v -> finishAndExit());

        Englishbtn.setOnClickListener(v -> startPushTalkActivity("en-US", "en", "MainActivity"));
        Arabicbtn.setOnClickListener(v -> startPushTalkActivity("ar-SA", "ar", "MainActivity"));
        Frenchbtn.setOnClickListener(v -> startPushTalkActivity("fr-FR", "fr", "MainActivity"));
        Chinesebtn.setOnClickListener(v -> startPushTalkActivity("zh-tw", "zh", "MainActivity"));
    }

    private void finishAndExit() {
        finish();
        System.exit(0);
    }

    private void startPushTalkActivity(String lang, String textLang, String pushTalk) {
        String url = "http://conversation.qltyss.com/sentence";

        Intent intent = new Intent(MainActivity.this, Push_TalkActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("langg", lang);
        bundle.putString("textlang", textLang);
        bundle.putString("pushtalk", pushTalk);
        intent.putExtras(bundle);

        startActivity(intent);

        performNetworkRequest(url, lang);
    }

    private void performNetworkRequest(String url, String lang) {
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                response -> Log.d("TAG", "onResponse: "),
                error -> Log.e("Error", "onErrorResponse: ", error)) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public byte[] getBody() {
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("lang", lang);
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
}



