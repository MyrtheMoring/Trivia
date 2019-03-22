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

public class level extends AppCompatActivity implements View.OnClickListener {

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

        Intent intent = new Intent(this, trivia_game.class);
        level = v.getTag().toString();

        intent.putExtra("level", level);
        intent.putExtra("category", category);
        startActivity(intent);
    }


//    @Override
//    public void gotMenuItems(ArrayList<Game> menuItems) {
//
//        LevelAdapter menu_adapter = new LevelAdapter(this, R.layout.content_question, menuItems);
//        ListView listView = (ListView) findViewById(R.id.level_list);
//        listView.setAdapter(menu_adapter);
//        listView.setOnItemClickListener(new MenuItemClickListener());
//    }
//
//    @Override
//    public void gotMenuItemsError(String message) {
//
//        // If there was a problem loading the categories display the error
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
//    }
//
//    private class MenuItemClickListener implements AdapterView.OnItemClickListener {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            // Create the intent
//            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
//
//            // Add the clicked MenuItem to the intent
//            MenuItem menuItem = (MenuItem) parent.getItemAtPosition(position);
//            intent.putExtra("item", menuItem);
//            // Start the activity
//            startActivity(intent);
//        }
//    }


}
