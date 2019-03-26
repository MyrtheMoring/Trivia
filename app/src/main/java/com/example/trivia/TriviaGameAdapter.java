package com.example.trivia;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class TriviaGameAdapter extends ArrayAdapter<Question> {

    private ArrayList<Question> questions;

    // Create constructor
    public TriviaGameAdapter(Context context, int resource, ArrayList<Question> objects) {
        super(context, resource, objects);
        questions = objects;
    }

    /** Set the questions and answers (shuffled). Set the tag for the correct answer to check on. */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.content_question, parent, false);
        }

        Question qItem = questions.get(position);

        TextView question = convertView.findViewById(R.id.question);
        RadioButton correct_answer = convertView.findViewById(R.id.b1);
        RadioButton incorrecta1 = convertView.findViewById(R.id.b2);
        RadioButton incorrecta2 = convertView.findViewById(R.id.b3);
        RadioButton incorrecta3 = convertView.findViewById(R.id.b4);

        ArrayList<String> answers_incorrect = qItem.getIncorrect_answers();
        qItem.setIncorrect_answers(answers_incorrect);
        String c_answer = qItem.getCorrect_answer();
        qItem.setCorrect_answer(c_answer);
        answers_incorrect.add(c_answer);

        Collections.shuffle(answers_incorrect);

        question.setText(Html.fromHtml(qItem.getQuestion(),Html.FROM_HTML_MODE_LEGACY));
        incorrecta1.setText(Html.fromHtml(answers_incorrect.get(0),Html.FROM_HTML_MODE_LEGACY));
        incorrecta2.setText(Html.fromHtml(answers_incorrect.get(1),Html.FROM_HTML_MODE_LEGACY));
        incorrecta3.setText(Html.fromHtml(answers_incorrect.get(2),Html.FROM_HTML_MODE_LEGACY));
        correct_answer.setText(Html.fromHtml(answers_incorrect.get(3),Html.FROM_HTML_MODE_LEGACY));

        correct_answer.setTag("True");

        return convertView;
    }
}
