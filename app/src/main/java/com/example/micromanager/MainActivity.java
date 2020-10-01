package com.example.micromanager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.ImageView;

        import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String PRIMARY_CHANNEL_ID="primary_notification_channel";
    private NotificationManager notifyManager;
    private static final int NOTIFICATION_ID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView logo = (ImageView) findViewById(R.id.logo);
        int logoRes = logo.getResources().getIdentifier("@drawable/logo", null,this.getPackageName());
        logo.setImageResource(logoRes);
        createNotificationChannel();
        AssignmentViewModel assignmentViewModel = new ViewModelProvider(this).get(AssignmentViewModel.class);
        List<AssignmentTable> overDueAssignments = assignmentViewModel.getOverdueAssignments();
        if(overDueAssignments.size() > 0){
            for(int i = 0; i < overDueAssignments.size(); i++){
                String overdueAssignmentName = overDueAssignments.get(i).name;
                sendNotification(overdueAssignmentName);
            }
        }
    }

    public void createNotificationChannel(){
        notifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, "Mascot Notification", NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.enableVibration(true);
        notificationChannel.setDescription("Notification");
        notifyManager.createNotificationChannel(notificationChannel);
    }

    private NotificationCompat.Builder getNotificationBuilder(String overdueAssignmentName){
        Intent notificationIntent = new Intent(this, Assignment_List.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID).setContentTitle("Check your Schedule! ")
                .setContentText(overdueAssignmentName + " is overdue!").setSmallIcon(R.drawable.ic_notification).setContentIntent(notificationPendingIntent)
                .setAutoCancel(true).setPriority(NotificationCompat.PRIORITY_HIGH).setDefaults(NotificationCompat.DEFAULT_ALL);
        return notifyBuilder;
    }

    public void sendNotification(String overdueAssignmentName){
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder(overdueAssignmentName);
        notifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
    }


    public void addAssignments(View view) {
        Intent intent = new Intent(this,Add_Screen.class);
        startActivity(intent);
    }

    public void goToAssignmentListScreen(View view) {
        Intent intent  = new Intent(this, Assignment_List.class);
        startActivity(intent);
    }

    public void goToHelpScreen(View view) {
        Intent intent = new Intent(this, Help_Page.class);
        startActivity(intent);
    }
    public void goToAboutPage(View view){
        Intent intent = new Intent(this, About_Page.class);
        startActivity(intent);
    }

}