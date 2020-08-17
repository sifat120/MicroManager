package com.example.micromanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class Schedule extends AppCompatActivity {
    public List<Assignment> assignments = new ArrayList<Assignment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

    }


    public void scheduleGenerationAlgorithm(){

        sortAssignments(assignments);
    }

    private void sortAssignments(List<Assignment> assignments) {
        for(int i = 0; i < assignments.size() - 1; i++){

        }
    }


}