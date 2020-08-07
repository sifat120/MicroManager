package com.example.micromanager;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView logo = (ImageView) findViewById(R.id.logo);
        int logoRes = logo.getResources().getIdentifier("@drawable/logo", null,this.getPackageName());
        logo.setImageResource(logoRes);
    }


    public void addAssignments(View view) {
        Intent intent = new Intent(this,Add_Screen.class);
        startActivity(intent);
    }

    public void goToAssignmentListScreen(View view) {
        Intent intent  = new Intent(this, Assignment_List.class);
        startActivity(intent);
    }
}