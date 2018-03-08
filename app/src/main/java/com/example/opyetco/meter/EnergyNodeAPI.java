package com.example.opyetco.meter;

import android.support.annotation.NonNull;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by Opyetco on 3/8/2018.
 */

public class EnergyNodeAPI  {
    private Volley volley;

    private RequestQueue requestQueue;

    public void getEnergyName(){
    }

    public void getAddress(){}

    public void loginUser(){

    }
    public void getCategoryNode(){}

    public void getLastReading (){
        RequestQueue resquest = new RequestQueue(Constants.LAST_READING);
    }
    public void setReading(){}

    public void getindex(){

    }

}
