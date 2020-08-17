package com.example.micromanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Schedule extends AppCompatActivity {
    public List<Assignment> assignments = new ArrayList<Assignment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Intent intent = getIntent();
        int keyNum = intent.getIntExtra("numOfKeys",0);
        Log.d("KEY NUM IS", "" + keyNum);
        List<String> keys = new ArrayList<String>();
        for(int i = 1; i <=keyNum; i++){
            String name = prefs.getString("Name" + i, "");
            String type = prefs.getString("Type" + i, "");
            String dueDate = prefs.getString(("Due Date" + i), "");
            keys.add("Name" + i);
            keys.add("Type" + i);
            keys.add("Due Date" + i);
            createAssignmentAndAddToList(name, type, dueDate);
        }
//        String test = "";
//        for(int j = 0; j < assignments.size();j++){
//            test+=assignments.get(j).toString() +" ";
//        }
//        Log.d("Current List: ", test);
    }

    public void createAssignmentAndAddToList(String name, String type, String dueDate){
        assignments.add(new Assignment(name, type, dueDate));
    }

    public void scheduleGenerationAlgorithm(List<Assignment> assignments){
        sortAssignments(assignments);
    }

    private void sortAssignments(List<Assignment> assignments) {
        for(int i = 0; i < assignments.size(); i++){

        }
    }


}