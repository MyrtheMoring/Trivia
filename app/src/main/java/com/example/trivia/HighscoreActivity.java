package com.example.trivia;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class HighscoreActivity extends AppCompatActivity implements TriviaGameRequest.ScoreCallback  {

    HighscoreAdapter adapter;

    /** The adapter in order to store the scores from the server. */
    public class HighscoreAdapter extends ArrayAdapter {

        private ArrayList<String> scores;

        /** Set name and score in ListView entry. */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.content_highscore, parent, false);
            }

            TextView name = ((TextView) convertView.findViewById(R.id.name));
            name.setText(scores.get(position));
            TextView highscore = ((TextView) convertView.findViewById(R.id.score));
            highscore.setText(scores.get(position));

            return convertView;
        }

        /** Adapter constructor. */
        public HighscoreAdapter(Context context, int resource, ArrayList<String> objects) {
            super(context, resource, objects);
            scores = objects;
        }
    }

    /** On creation, obtain highscores by making a get request to flask server. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        TriviaGameRequest req = new TriviaGameRequest(this);
        req.getScores(this);
    }

    /** If scores are retrieved, set the adapter to display them in ListView. */
    @Override
    public void gotScores(ArrayList<String> scores) {
        ListView scores_list = ((ListView) findViewById(R.id.listscores));
        adapter = new HighscoreAdapter(this, R.layout.content_highscore, scores);
        scores_list.setAdapter(adapter);
    }

    /** Catches the error when trying to obtain the scores. */
    @Override
    public void gotScoresError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
