package com.example.micromanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Assignment_List extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment__list);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        AssignmentViewModel aViewModel = new ViewModelProvider(this).get(AssignmentViewModel.class);

        List<AssignmentTable> assignmentTable = new ArrayList<>();
        ItemAdapter itemAdapter = new ItemAdapter(assignmentTable);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        aViewModel.getItems().observe(this,new Observer<List<AssignmentTable>>(){
            @Override
            public void onChanged(List<AssignmentTable> assignmentTables) {
                itemAdapter.setData(assignmentTables);
                itemAdapter.notifyDataSetChanged();
            }
        });
    }

}