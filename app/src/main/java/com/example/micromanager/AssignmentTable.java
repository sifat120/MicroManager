package com.example.micromanager;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "assignment")
public class AssignmentTable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name="assignment_name")
    public String name;

    @ColumnInfo(name="due_date")
    public String dueDate;

    @ColumnInfo(name="assignment_type")
    public String type;

    @ColumnInfo(name="highPriority")
    public boolean isHighPriority;

    @ColumnInfo(name="isCompleted")
    public boolean isCompleted;
}
