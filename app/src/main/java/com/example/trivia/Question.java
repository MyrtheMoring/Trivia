package com.example.trivia;

import java.util.ArrayList;

/** The class for the question. */
public class Question {

    private String question, correct_answer;
    private ArrayList<String> incorrect_answers;

    /** The Question constructor. */
    public Question(String question, String correct_answer, ArrayList<String> incorrect_answers){
        this.question = question;
        this.correct_answer = correct_answer;
        this.incorrect_answers = incorrect_answers;
    }

    public ArrayList<String> getIncorrect_answers() {
        return incorrect_answers;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public void setIncorrect_answers(ArrayList<String> incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }
}
