package com.example.opyetco.meter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ReadingsActivity extends AppCompatActivity {

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
        Intent i = new Intent(this,HistoryActivity.class);//correct classname
        startActivity(i);
    }
}
