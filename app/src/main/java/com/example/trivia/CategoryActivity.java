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

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener{

    String category;

    /** Manually set five different types of categories. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Button c_art = findViewById(R.id.art);
        Button c_animals = findViewById(R.id.animals);
        Button c_history = findViewById(R.id.history);
        Button c_geography = findViewById(R.id.geography);
        Button c_politics = findViewById(R.id.politics);

        c_art.setOnClickListener(this);
        c_animals.setOnClickListener(this);
        c_history.setOnClickListener(this);
        c_geography.setOnClickListener(this);
        c_politics.setOnClickListener(this);
    }

    /** The onClick function for all the category buttons. */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, LevelActivity.class);
        switch (v.getId()) {
            case R.id.art:
                category = "22";
            break;
            case R.id.animals:
                category = "27";
                break;
            case R.id.history:
                category = "23";
                break;
            case R.id.geography:
                category = "18";
                break;
            case R.id.politics:
                category = "24";
                break;
        }
        intent.putExtra("category", category);
        startActivity(intent);
    }
}
