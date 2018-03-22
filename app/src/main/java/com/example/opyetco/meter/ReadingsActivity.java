package com.example.opyetco.meter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadingsActivity extends AppCompatActivity {

    private List readings = new ArrayList();
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readings);

        new GetLastReading().execute();
    }
    public void openSetIndex(View view){
        Intent i = new Intent(this,SetIndex.class);
        startActivity(i);
    }
    public void openHistory(View view){
        Log.d("History reach", "going to history page");
        Intent i = new Intent(this,DayReadingActivity.class);//correct classname
        startActivity(i);
    }

    private class GetLastReading extends AsyncTask<Void, Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(ReadingsActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler make = new HttpHandler();
            String readingObj = make.makeServiceCall(Constants.LAST_READING+ "?node=1");
            try {
                JSONObject reading = new JSONObject(readingObj);
                HashMap<String,String> lastReading = new HashMap<>();
                lastReading.put("reading",reading.getString("reading"));
                lastReading.put("dayAccumulation",reading.getString("dayAccumulation"));
                lastReading.put("monthAccumulation", reading.getString("monthAccumulation"));
                lastReading.put("yearAccumulation" , reading.getString("yearAccumulation"));
                readings.add(lastReading);

            }catch (JSONException except){
                Log.e("JSonException", except.getMessage());
            }

            return null;
        }
    }
}
