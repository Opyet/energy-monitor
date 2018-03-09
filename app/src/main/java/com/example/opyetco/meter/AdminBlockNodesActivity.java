package com.example.opyetco.meter;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

//import static com.example.opyetco.meter.AdminBlockNodesActivity.REQUEST_URL;

public class AdminBlockNodesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_blocks_nodes);
    }

    public void openReading(View view){

        Intent i = new Intent(this,ReadingsActivity.class);
        startActivity(i);
    }

}