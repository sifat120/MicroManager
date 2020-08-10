package com.example.micromanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class Schedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);


    }



    private void scheduleGenerationAlgorithm(List<Assignment> assignmentList){

    }


}