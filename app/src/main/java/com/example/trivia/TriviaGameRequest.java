package com.example.trivia;

import android.content.Context;

import java.util.ArrayList;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TriviaGameRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private ArrayList<Question> questions = new ArrayList<Question>();
    private ArrayList<String> scores = new ArrayList<>();
    private Context context;
    private Callback questionsCB;
    private PostCallback postCB;
    private ScoreCallback scoreCB;

    public String score, name;

    private final String IDEURL = "https://ide50-myrthehelene.legacy.cs50.io:8080/post";

    /** Constructor for the Trivia Game. */
    public TriviaGameRequest(Context c) {
        context = c;
    }

    /** Interface for the questions callback. */
    public interface Callback {
        void gotQuestions(ArrayList<Question> questions);
        void gotQuestionsError(String message);
    }

    /** Interface for the post scores callback. */
    public interface PostCallback {
        void postScore();
        void postScoreError(String message);
    }

    /** Interface for the scores callback. */
    public interface ScoreCallback {
        void gotScores(ArrayList<String> scores);
        void gotScoresError(String message);
    }

    /** Get questions from the request. */
    public void getQuestions(Callback cb, String category, String level) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://opentdb.com/api.php?amount=10&category=" +
                category + "&difficulty=" + level + "&type=multiple";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,
                null, this, this);
        queue.add(jsonObjectRequest);
        questionsCB = cb;
    }

    /** To get the scores from the Callback. */
    public void getScores(ScoreCallback callback) {
        this.scoreCB = callback;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrReq = new JsonArrayRequest(
                Request.Method.GET,
                this.IDEURL,
                null,
                new onScoresResponseListener(),
                new onScoresResponseListener()
        );
        queue.add(jsonArrReq);
    }

    /** The onResponse function for the */
    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray results = response.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject item = results.getJSONObject(i);
                String question = item.getString("question");
                String correct_answer = item.getString("correct_answer");
                JSONArray incorrect_ans = item.getJSONArray("incorrect_answers");
                ArrayList<String> incorrect_answers = new ArrayList<String>();
                for (int j=0;j<incorrect_ans.length();j++){
                    incorrect_answers.add(incorrect_ans.getString(j));
                }
                Question q  = new Question(question, correct_answer, incorrect_answers);
                questions.add(q);
            }
            questionsCB.gotQuestions(questions);

        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    /** When there is an error with the Volley. */
    @Override
    public void onErrorResponse(VolleyError error) {
        questionsCB.gotQuestionsError(error.getMessage());
    }



    /** Responselistener for request to post highscore. */
    public class onPostScoresResponseListener implements Response.Listener<JSONObject>, Response.ErrorListener{

        @Override
        public void onResponse(JSONObject response) {
            postCB.postScore();
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            postCB.postScoreError(error.getMessage());

        }
    }

    /** Response listener for request to obtain all highscores. */
    /** If a successful response is returned, add all highscores to an arraylist and return
     * it through the callback. */
    public class onScoresResponseListener implements Response.Listener<JSONArray>, Response.ErrorListener{

        @Override
        public void onResponse(JSONArray response) {
            try {
                for (int i = 0; i < response.length(); i++) {
                    String name = response.getJSONObject(i).getString("name");
                    String score = response.getJSONObject(i).getString("score");
                    scores.add(score);
                }
                scoreCB.gotScores(scores);
            } catch (JSONException e) {
                scoreCB.gotScoresError(e.getMessage());
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            scoreCB.gotScoresError(error.getMessage());
        }
    }

}
