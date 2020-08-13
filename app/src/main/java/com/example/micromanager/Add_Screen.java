package com.example.micromanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Screen extends AppCompatActivity {
    public int keyNum = 0;
    SharedPreferences.Editor editor;
    SharedPreferences localPrefs;
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

        localPrefs = this.getPreferences(Context.MODE_PRIVATE);

        Toolbar registrationToolbar = findViewById(R.id.registrationToolbar);
        setSupportActionBar(registrationToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void addAssignmentsToGenList(View view) {
        name = nameField.getText().toString();
        type = typeField.getText().toString();
        due_date = dueDateField.getText().toString();

        editor = localPrefs.edit();
        ++keyNum;
        String nameKey = getString(R.string.nameText) + keyNum;
        String typeKey = getString(R.string.typeText) + keyNum;
        String dueDateKey = getString(R.string.dueDateText) + keyNum;
        editor.putString(nameKey, name);
        editor.putString(typeKey, type);
        editor.putString(dueDateKey, due_date);
        Log.d("NAME TYPE DUE DATE", nameKey + " " + typeKey + " "+ dueDateKey);
        editor.apply();

        nameField.getText().clear();
        typeField.getText().clear();
        dueDateField.getText().clear();

        Toast.makeText(this,"Assignment Added", Toast.LENGTH_SHORT).show();

    }


    public void goToScheduleScreen(View view) {
        Intent intent = new Intent(this, Schedule.class);
        intent.putExtra("numOfKeys", keyNum);
        startActivity(intent);
    }
}