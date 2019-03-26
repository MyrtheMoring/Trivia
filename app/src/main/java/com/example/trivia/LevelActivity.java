package com.example.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LevelActivity extends AppCompatActivity implements View.OnClickListener {

    String level, category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        Button easy = findViewById(R.id.easy);
        Button medium = findViewById(R.id.medium);
        Button hard = findViewById(R.id.hard);

        easy.setOnClickListener(this);
        medium.setOnClickListener(this);
        hard.setOnClickListener(this);

        Intent intent = getIntent();
        category = intent.getStringExtra("category");
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this, TriviaGameActivity.class);
        level = v.getTag().toString();

        intent.putExtra("level", level);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}
