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

public class category extends AppCompatActivity implements View.OnClickListener{

    String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

//        CategoryRequest categories_request = new CategoryRequest(this);
//        categories_request.getCategories(this);

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

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, level.class);
        switch (v.getId()) {
            case R.id.art:
                category = "22";
            break;
            case R.id.animals:
                category = "22";
                break;
            case R.id.history:
                category = "22";
                break;
            case R.id.geography:
                category = "22";
                break;
            case R.id.politics:
                category = "22";
                break;
        }
        intent.putExtra("category", category);
        startActivity(intent);
    }

//    @Override
//    public void gotCategories(ArrayList<String> categories) {
//
//        ListView listview = findViewById(R.id.categorylist);
//        categoriesAdapter = new CategoryAdapter(this, R.layout.content_question, categories);
//        listview.setAdapter(categoriesAdapter);
//        listview.setOnItemClickListener(new CategoryClickListener());
//    }
//
//    @Override
//    public void gotCategoriesError(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
//
//    }
//
//    private class CategoryClickListener implements AdapterView.OnItemClickListener {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            Intent intent = new Intent(category.this, level.class);
//
//            String category = parent.getItemAtPosition(position).toString();
//            intent.putExtra("category", category);
//            startActivity(intent);
//        }
//    }

}
