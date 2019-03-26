package com.example.trivia;

import java.io.Serializable;

/** Class to collect name and highscore of that person. */
public class Highscore implements Serializable {

    String name, score;

    public Highscore(String name, String score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }
}
