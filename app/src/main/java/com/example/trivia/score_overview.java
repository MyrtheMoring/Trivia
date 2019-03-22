package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class score_overview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_overview);

        Intent intent = getIntent();
        String score = intent.getStringExtra("score");

        TextView scoretext = findViewById(R.id.score);
        scoretext.setText(score + " out of 10");
    }
}
