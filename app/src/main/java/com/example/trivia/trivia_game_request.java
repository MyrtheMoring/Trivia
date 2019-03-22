package com.example.trivia;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class trivia_game_request implements Response.Listener<JSONObject>, Response.ErrorListener {


    private static Callback activity_set;
    private ArrayList<Question> questions = new ArrayList<Question>();
    private static Context context;

    public interface Callback {
        void gotQuestions(ArrayList<Question> questions);
        void gotQuestionsError(String message);
    }

    public trivia_game_request(Context c) {
        context = c;
    }


    public void getQuestions(Callback activity, String category, String level) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://opentdb.com/api.php?amount=10&category=" + category + "&difficulty=" + level + "&type=multiple";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
        activity_set = activity;
    }

    // This method is called when there is an error with the volley
    @Override
    public void onErrorResponse(VolleyError error) {
        activity_set.gotQuestionsError(error.getMessage());
    }

    // This method is called when there is no error with the volley
    @Override
    public void onResponse(JSONObject response) {
        Log.d("RESPONSECHECK", response.toString());
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
            activity_set.gotQuestions(questions);

        }
        catch (JSONException e){
            Log.d("Error JSON", e.toString());
            e.printStackTrace();
        }

    }
}
