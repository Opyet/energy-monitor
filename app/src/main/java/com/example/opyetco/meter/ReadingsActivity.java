package com.example.opyetco.meter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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
    private TextView dayReading, monthReading, yearReading;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readings);
        dayReading = (TextView) findViewById(R.id.nodeReading);
        monthReading = (TextView) findViewById(R.id.monthReading);
        yearReading = (TextView) findViewById(R.id.yearReading) ;

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

    private class GetLastReading extends AsyncTask<Void, Void,HashMap<String,String>>{
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
        protected HashMap<String,String> doInBackground(Void... voids) {
            HttpHandler make = new HttpHandler();
            String readingObj = make.makeServiceCall(Constants.LAST_READING+ "?node=1");
            HashMap<String,String> lastReading = new HashMap<>();
            try {
                JSONObject reading = new JSONObject(readingObj);
                lastReading.put("reading",reading.getString("reading"));
                lastReading.put("dayAccumulation",reading.getString("dayAccumulation"));
                lastReading.put("monthAccumulation", reading.getString("monthAccumulation"));
                lastReading.put("yearAccumulation" , reading.getString("yearAccumulation"));
                Log.e("TryReading", lastReading.toString());

            }catch (JSONException except){
                Log.e("JSonException", except.getMessage());
            }
            return  lastReading;
        }
        @Override
        protected void onPostExecute(HashMap<String,String> result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            Log.e("ReadingList", result.toString());
            Log.e("dayAccumulation", dayReading.toString());

            dayReading.setText(result.get("dayAccumulation"));
            monthReading.setText(result.get("monthAccumulation"));
            yearReading.setText(result.get("yearAccumulation"));

        }
    }
}
