package com.example.micromanager;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AssignmentDao {
    @Insert
    void insertItem(AssignmentTable assignmentTables);

    @Query("SELECT * FROM assignment")
    LiveData<List<AssignmentTable>> getAllItems();


    @Delete
    void deleteItem(AssignmentTable assignmentTable);
}
