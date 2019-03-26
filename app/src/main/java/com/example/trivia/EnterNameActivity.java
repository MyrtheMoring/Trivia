package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class EnterNameActivity extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener {

    private String score;
    private static String URL = "https://ide50-myrthehelene.legacy.cs50.io:8080/post";

    /** Sets the activity and get the score from the intent to print.*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name);

        Intent intent = getIntent();
        score = intent.getStringExtra("score");

        TextView scoretext = findViewById(R.id.score);
        scoretext.setText(score + " out of 10");
    }

    /** The button onClick function to save the score.*/
    public void saveScore(View v) {
        RequestQueue queue = Volley.newRequestQueue(this);

        String nameinput = ((EditText) findViewById(R.id.nameinput)).getText().toString();
        PostRequest request = new PostRequest(Request.Method.POST, URL, this, this,
                nameinput, score);

        if (nameinput != "") {
            queue.add(request);
            Intent intent = new Intent(this, HighscoreActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Not a valid name", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(String response) {
        Toast.makeText(this, response, Toast.LENGTH_LONG).show();
    }
}
