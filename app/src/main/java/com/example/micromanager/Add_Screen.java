package com.example.micromanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.channels.InterruptedByTimeoutException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Add_Screen extends AppCompatActivity {
    EditText nameField, dueDateField, typeField;
    Button addButton;
    String name, type, due_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__screen);

        nameField = (EditText) findViewById(R.id.Name_Field);
        dueDateField = (EditText) findViewById(R.id.Due_Date_Field);
        typeField = (EditText) findViewById(R.id.Type_Field);
        addButton = (Button) findViewById(R.id.add_button);


        Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(dueDateField,myCalendar);
        };

        dueDateField.setOnClickListener(v -> {
            new DatePickerDialog(Add_Screen.this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });




        Toolbar registrationToolbar = findViewById(R.id.registrationToolbar);
        setSupportActionBar(registrationToolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void updateLabel(EditText edittext, Calendar myCalendar) {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));

    }

    public void addAssignmentToDatabase(View view) {
        name = nameField.getText().toString();
        type = typeField.getText().toString();
        due_date = dueDateField.getText().toString();

        AssignmentViewModel aViewModel = new ViewModelProvider(this).get(AssignmentViewModel.class);

        //making the entry
        AssignmentTable assignmentTable = new AssignmentTable();
        assignmentTable.name = name;
        assignmentTable.dueDate = due_date;
        assignmentTable.type = type;

        //setting priority to not high and iscompleted to false
        assignmentTable.isCompleted = false;
        assignmentTable.isHighPriority = false;

        nameField.getText().clear();
        typeField.getText().clear();
        dueDateField.getText().clear();

        aViewModel.insertNewItem(assignmentTable); //ADD TO THE DATABASE

        Toast.makeText(this,"Assignment Added", Toast.LENGTH_SHORT).show();

    }


    public void goToAssignmentListScreen(View view) {
        Intent intent = new Intent(this, Assignment_List.class);
        startActivity(intent);
    }

}