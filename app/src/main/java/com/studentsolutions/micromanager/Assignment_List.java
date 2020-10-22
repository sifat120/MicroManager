package com.studentsolutions.micromanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
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
                    Log.d("REACHED","CATCH STATEMENT");
                }
                assert date1 != null;
                return date1.after(date2) ? 1 : (date1.before(date2)) ? -1 : 0;
            });
            itemAdapter.setData(assignmentTables);
            itemAdapter.notifyDataSetChanged();
        });
        Toolbar registrationToolbar = findViewById(R.id.backtohomeBar);
        setSupportActionBar(registrationToolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                aViewModel.deleteItem(itemAdapter.getAssignmentTableAt(viewHolder.getAdapterPosition()));
                itemAdapter.notifyDataSetChanged();
                itemAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                Toast.makeText(Assignment_List.this,"Deleted!",Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

    }


    public void addAssignments(View view) {
        Intent intent = new Intent(this,Add_Screen.class);
        startActivity(intent);
    }


    public void goToHelpScreen(View view) {
        Intent intent = new Intent(this, Help_Page.class);
        startActivity(intent);
    }

    public void tidyUpAssignments(View view) {
        AssignmentViewModel assignmentViewModel = new ViewModelProvider(this).get(AssignmentViewModel.class);
        assignmentViewModel.deleteAllCompletedAssignments();
        Toast.makeText(this, "Good Work!", Toast.LENGTH_SHORT).show();
    }
}