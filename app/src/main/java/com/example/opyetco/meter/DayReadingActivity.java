package com.example.opyetco.meter;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by kibb on 3/19/18.
 */

public class DayReadingActivity extends AppCompatActivity {

    private String TAG = DayReadingActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;
    ArrayList<HashMap<String, String>> readingList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        readingList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list_view);
        new GetDayReading().execute();
    }

    private class GetDayReading extends AsyncTask<Void,Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(DayReadingActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        protected Void doInBackground(Void... arg0) {
            //getting today's date
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(Constants.DAY_READING + "?node=1&date="+date);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray response = new JSONArray(jsonStr);

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject details = response.getJSONObject(i);
                       String nodeId = details.getString("nodeId");
                         String reading = details.getString("reading");
                       String created = details.getString("created");
                       String timeArr[] = created.split(" ");
                       created = timeArr[1];
                        String dayAccumulation = details.getString("dayAccumulation");
                        String monthAccumulation = details.getString("monthAccumulation");
                        String yearAccumulation = details.getString("yearAccumulation");

                        HashMap<String,String> new_reading = new HashMap<>();
                        new_reading.put("nodeId",nodeId);
                        new_reading.put("reading",reading);
                        new_reading.put("created",created);
                        new_reading.put("dayAccumulation",dayAccumulation);
                        new_reading.put("month",monthAccumulation);
                        new_reading.put("yearAccumulation",yearAccumulation);

                        readingList.add(new_reading);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Today, no reading has been saved",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get data from server. Check your internet connection!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            Log.e("ReadingList", readingList.toString());
            ListAdapter adapter = new SimpleAdapter(
                    DayReadingActivity.this,readingList, R.layout.reading_list,
                    new String[]{"created","reading"},
                    new int[]{R.id.time, R.id.reading}
            );

            lv.setAdapter(adapter);
        }


    }
}