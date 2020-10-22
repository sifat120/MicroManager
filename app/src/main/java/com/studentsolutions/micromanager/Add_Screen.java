package com.studentsolutions.micromanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Add_Screen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText nameField, dueDateField;
    Spinner typeField;
    Button addButton;
    String name, type, due_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__screen);

        nameField = (EditText) findViewById(R.id.Name_Field);
        dueDateField = (EditText) findViewById(R.id.Due_Date_Field);
        dueDateField.setFocusable(false);
        typeField =  (Spinner) findViewById(R.id.Type_Field);
        addButton = (Button) findViewById(R.id.Add_Button);

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

        Toolbar registrationToolbar = findViewById(R.id.backtohomeBar);
        setSupportActionBar(registrationToolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.type_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeField.setAdapter(adapter);
        typeField.setOnItemSelectedListener(this);
    }

    private void updateLabel(EditText edittext, Calendar myCalendar) {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        edittext.setText(sdf.format(myCalendar.getTime()));

    }


    public void checkTextFields(View view){
        name = nameField.getText().toString();
        due_date = dueDateField.getText().toString();
        if(due_date == null || due_date.equals("")) {
            Toast.makeText(this, "Missing Field", Toast.LENGTH_SHORT).show();
        }else{
            addAssignmentToDatabase(name, type, due_date);
        }
    }

    public void addAssignmentToDatabase(String name, String type, String due_date){
        AssignmentViewModel aViewModel = new ViewModelProvider(this).get(AssignmentViewModel.class);

        //making the entry
        AssignmentTable assignmentTable = new AssignmentTable();
        assignmentTable.name = name;
        assignmentTable.dueDate = due_date;
        assignmentTable.type = type;

        //setting priority to not high and is completed to false
        assignmentTable.isCompleted = false;

        nameField.getText().clear();
        dueDateField.getText().clear();

        aViewModel.insertNewItem(assignmentTable); //ADD TO THE DATABASE

        Toast.makeText(this,"Assignment Added", Toast.LENGTH_SHORT).show();

    }


    public void goToAssignmentListScreen(View view) {
        Intent intent = new Intent(this, Assignment_List.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        type = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        
    }
}