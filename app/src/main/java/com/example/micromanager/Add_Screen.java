package com.example.micromanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class Add_Screen extends AppCompatActivity {

    //SharedPreferences pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
  //  SharedPreferences.Editor editor = pref.edit();
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
    }

    public void goBackToHomeScreen(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addAssignmentsToGenList(View view) {
       name = nameField.getText().toString();
        type = typeField.getText().toString();
        due_date = dueDateField.getText().toString();
      /*  editor.putString("Name", name);
        editor.putString("Type", type);
        editor.putString("Due Date", due_date);
        editor.commit();*/

    }
}