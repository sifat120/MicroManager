package com.example.micromanager;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AssignmentDao {
    @Insert
    void insertItem(AssignmentTable assignmentTables);

    @Query("SELECT * FROM assignment")
    LiveData<List<AssignmentTable>> getAllItems();


    @Query("DELETE FROM assignment WHERE isCompleted = 1")
    void deleteAllMarkedAsDone();

    @Query("SELECT * FROM assignment WHERE isOverdue = 1")
    List<AssignmentTable> getOverdueAssignments();

    @Update
    void update(AssignmentTable assignmentTable);

    @Delete
    void deleteItem(AssignmentTable assignmentTable);
}
