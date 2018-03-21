package com.example.opyetco.meter;

import android.content.Intent;
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

public class ReadingsActivity extends AppCompatActivity {
    private String reading ;
    private String time ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readings);
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
}
