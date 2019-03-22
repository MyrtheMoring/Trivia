package com.example.trivia;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class trivia_game_adapter extends ArrayAdapter<Question> {

    private ArrayList<Question> questions;

    // Create constructor
    public trivia_game_adapter(Context context, int resource, ArrayList<Question> objects) {
        super(context, resource, objects);
        questions = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Load item if it has not been loaded before
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.content_question, parent, false);
        }

        // Get the menu item from the list
        Question qItem = questions.get(position);

        TextView question = convertView.findViewById(R.id.question);
        RadioButton correct_answer = convertView.findViewById(R.id.b1);
        RadioButton incorrecta1 = convertView.findViewById(R.id.b2);
        RadioButton incorrecta2 = convertView.findViewById(R.id.b3);
        RadioButton incorrecta3 = convertView.findViewById(R.id.b4);
//
//        correct_answer.setChecked(false);
//        incorrecta1.setChecked(false);
//        incorrecta2.setChecked(false);
//        incorrecta3.setChecked(false);

        ArrayList<String> answers = qItem.getIncorrect_answers();
        answers.add(qItem.getCorrect_answer());
        Collections.shuffle(answers);

        question.setText(Html.fromHtml(qItem.getQuestion(),Html.FROM_HTML_MODE_LEGACY));
        incorrecta1.setText(Html.fromHtml(answers.get(0),Html.FROM_HTML_MODE_LEGACY));
        incorrecta2.setText(Html.fromHtml(answers.get(1),Html.FROM_HTML_MODE_LEGACY));
        incorrecta3.setText(Html.fromHtml(answers.get(2),Html.FROM_HTML_MODE_LEGACY));
        correct_answer.setText(Html.fromHtml(answers.get(3),Html.FROM_HTML_MODE_LEGACY));

        correct_answer.setTag("True");
        incorrecta1.setTag("False");
        incorrecta2.setTag("False");
        incorrecta3.setTag("False");

        return convertView;
    }
}
