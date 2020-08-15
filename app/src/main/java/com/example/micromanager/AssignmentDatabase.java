package com.example.micromanager;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {AssignmentTable.class}, version = 1)
public abstract class AssignmentDatabase extends RoomDatabase {
    private static AssignmentDatabase assignmentDatabaseInstance;

    public static synchronized AssignmentDatabase getInstance(Context context){
        if(assignmentDatabaseInstance==null){
            assignmentDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(), AssignmentDatabase.class,
                    "myDatabase").build();
        }
        return assignmentDatabaseInstance;
    }


    public abstract AssignmentDao assignmentDao();
}
