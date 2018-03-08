package com.example.opyetco.meter;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }

    public void openAdminBlocksList(View view){
        Intent i = new Intent(this,AdminBlockNodesActivity.class);
        startActivity(i);
    }

    public void openLabList(View view){
        Intent i = new Intent(this,LaboratoryNodesActivity.class);
        startActivity(i);
    }

    public void openHostelList(View view){
        Intent i = new Intent(this,HostelsNodesActivity.class);
        startActivity(i);
    }

    public void openLectureHallList(View view){
        Intent i = new Intent(this,LectureHallsNodesActivity.class);
        startActivity(i);
    }

    public void openBusinessUnitList(View view){
        Intent i = new Intent(this,BusinessUnitNodesActivity.class);
        startActivity(i);
    }

    public void openOthersList(View view){
        Intent i = new Intent(this,OthersNodesActivity.class);
        startActivity(i);
    }
}
