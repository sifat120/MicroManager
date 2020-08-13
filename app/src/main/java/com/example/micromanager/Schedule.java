package com.example.micromanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        Intent intent = getIntent();
        int keyNum = intent.getIntExtra("numOfKeys",0);
        Log.d("KEY NUM IS", "" + keyNum);
        for(int i = 1; i <= keyNum; i++){
            String name = prefs.getString(getString(R.string.nameText) + i, "");
            String type = prefs.getString(getString(R.string.typeText) + i,"");
            String dueDate = prefs.getString(getString(R.string.dueDateText)+ i, "");
            Log.d("NAME, TYPE, DUE DATE", name +" " + type+ " "+ dueDate);
            createAssignmentAndAddToList(name, type, dueDate);
        }

        String test = "";
        for(int i = 0; i < assignments.size();i++){
            test+=assignments.get(i).toString();
        }
        Log.d("Current List: ", test);
    }

    public void createAssignmentAndAddToList(String name, String type, String dueDate){
        assignments.add(new Assignment(name, type, dueDate));

    }

    private void scheduleGenerationAlgorithm(List<Assignment> assignmentList){

    }


}