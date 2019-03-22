package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class trivia_game extends AppCompatActivity implements trivia_game_request.Callback{

    String category, level;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game);
        Intent intent = getIntent();
        category = intent.getStringExtra("category");
        level = intent.getStringExtra("level");

        score = 0;

        trivia_game_request request = new trivia_game_request(this);
        request.getQuestions(this, category, level);

    }



    public void check_score(View v) {
        Intent intent = new Intent(this, score_overview.class);
        intent.putExtra("score","Score "+score);
        startActivity(intent);
    }

    @Override
    public void gotQuestions(ArrayList<Question> questions) {
        trivia_game_adapter adapter = new trivia_game_adapter(this, R.layout.content_question, questions);
        ListView listView = (ListView) findViewById(R.id.q_list);
        listView.setAdapter(adapter);
    }

    @Override
    public void gotQuestionsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void questionCheck(View view) {
        String check = view.getTag().toString();
        if (check == "True"){
            score = score + 1;
        }
    }


}
