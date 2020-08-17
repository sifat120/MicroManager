package com.example.micromanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Assignment_List extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment__list);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        AssignmentViewModel aViewModel = new ViewModelProvider(this).get(AssignmentViewModel.class);

        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        List<AssignmentTable> assignmentTable = new ArrayList<>();
        final ItemAdapter itemAdapter = new ItemAdapter(assignmentTable);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        aViewModel.getItems().observe(this, assignmentTables -> {
            Collections.sort(assignmentTables, (assignmentTable1, assignmentTable2) -> {
                Date date1 = null;
                Date date2 = null;
                try {
                    date1 = sdf.parse(assignmentTable1.dueDate);
                    date2 = sdf.parse(assignmentTable2.dueDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return date1.after(date2) ? 1 : (date1.before(date2)) ? -1 : 0;
            });
            itemAdapter.setData(assignmentTables);
            itemAdapter.notifyDataSetChanged();
        });



    }

    public void addAssignments(View view) {
        Intent intent = new Intent(this,Add_Screen.class);
        startActivity(intent);
    }

    public void backToMain(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}