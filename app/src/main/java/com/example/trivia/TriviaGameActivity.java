package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TriviaGameActivity extends AppCompatActivity implements TriviaGameRequest.Callback{

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

        TriviaGameRequest request = new TriviaGameRequest(this);
        request.getQuestions(this, category, level);
    }

    /** The button onClick function to check the score after answering the questions.*/
    public void check_score(View v) {
        Intent intent = new Intent(this, EnterNameActivity.class);
        intent.putExtra("score","Score "+score);
        startActivity(intent);
    }

    /** Set the adapter with the questions.*/
    @Override
    public void gotQuestions(ArrayList<Question> questions) {
        TriviaGameAdapter adapter = new TriviaGameAdapter(this, R.layout.content_question, questions);
        ListView listView = (ListView) findViewById(R.id.q_list);
        listView.setAdapter(adapter);
    }

    /** When there is an error with obtaining the questions.*/
    @Override
    public void gotQuestionsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /** Function that checks if the clicked answer is true or not. */
    public void questionCheck(View view) {
        String check = view.getTag().toString();
        if (check == "True"){
            score = score + 1;
        }
    }
}
