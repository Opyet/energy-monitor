package com.example.opyetco.meter;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class HistoryActivity extends AppCompatActivity {
    private String nodeId;
    private String reading;
    private String created;
    private String dayAccumulation;
    private String monthAccumulation;
    private  String yearAccumulation;
    private ListView listView;
    ArrayList<HashMap<String, String>> readingList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        readingList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list_view);
       new GetReadingHistory().execute();
    }

    public void getDayReading(){
        JsonArrayRequest request = new JsonArrayRequest( Constants.DAY_READING,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.v("Day Reading", response.toString());
                        try{
                            for(int i =0; i < response.length(); i++){
                                JSONObject details = response.getJSONObject(i);
                                nodeId = details.getString("nodeId");
                                reading = details.getString("reading");
                                created = details.getString("created");
                                dayAccumulation = details.getString("dayAccumulation");
                                monthAccumulation = details.getString("monthAccumulation");
                                yearAccumulation = details.getString("yearAccumulation");

                                HashMap<String,String> new_reading = new HashMap<>();
                                new_reading.put("nodeId",nodeId);
                                new_reading.put("reading",reading);
                                new_reading.put("created",created);
                                new_reading.put("dayAccumulation",dayAccumulation);
                                new_reading.put("month",monthAccumulation);
                                new_reading.put("yearAccumulation",yearAccumulation);

                                readingList.add(new_reading);
                            }
                        }catch (JSONException ex){

                        }
                    }
                }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    Log.v("Error Response", error.toString());
                }
        });
    }

    private class GetReadingHistory extends AsyncTask<Void, Void, Void>{
        @Override
        protected  void onPreExecute(){

        }
        @Override
        protected Void doInBackground(Void ... arg) {
            JsonArrayRequest request = new JsonArrayRequest( Constants.DAY_READING,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.v("Day Reading", response.toString());
                            try{
                                for(int i =0; i < response.length(); i++){
                                    JSONObject details = response.getJSONObject(i);
                                    nodeId = details.getString("nodeId");
                                    reading = details.getString("reading");
                                    created = details.getString("created");
                                    dayAccumulation = details.getString("dayAccumulation");
                                    monthAccumulation = details.getString("monthAccumulation");
                                    yearAccumulation = details.getString("yearAccumulation");

                                    HashMap<String,String> new_reading = new HashMap<>();
                                    new_reading.put("nodeId",nodeId);
                                    new_reading.put("reading",reading);
                                    new_reading.put("created",created);
                                    new_reading.put("dayAccumulation",dayAccumulation);
                                    new_reading.put("month",monthAccumulation);
                                    new_reading.put("yearAccumulation",yearAccumulation);

                                    readingList.add(new_reading);
                                }
                            }catch (JSONException ex){
                                Log.v("Json Except",ex.toString());
                                Log.d("Json Exept", ex.toString());
                            }
                        }
                    }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    Log.v("Error Response", error.toString());
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(
                    HistoryActivity.this,readingList, R.layout.reading_list,
                    new String[]{"time","reading"},
                    new int[]{R.id.time, R.id.reading}
            );

            listView.setAdapter(adapter);
        }
    }
}
