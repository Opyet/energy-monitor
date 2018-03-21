package com.example.opyetco.meter;

import android.nfc.Tag;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by kibb on 3/19/18.
 */

public class HttpHandler {
    private static final String TAG = HttpHandler.class.getCanonicalName();

    public HttpHandler(){}
    public String makeServiceCall (String reqUrl){
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream in = new BufferedInputStream(connection.getInputStream());
            response = convertToString(in);
        }catch (MalformedURLException e){
            Log.e(TAG, "MalformedURL: " + e.getMessage());
        }catch (ProtocolException e){
            Log.e(TAG, "ProtolCol: " + e.toString());
        }catch (IOException e){
            Log.e(TAG,"IOEXception: " + e.getMessage());
        }catch (Exception e){
            Log.e(TAG,"Exception" + e.getMessage());
        }
        return response;
    }

    private String convertToString(InputStream is){
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line ;
        try {
            while ((line= reader.readLine()) != null){
                sb.append(line).append('\n');
            }
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            try{
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
