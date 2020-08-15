package com.example.micromanager;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemRepository {
    private Context context;

    public ItemRepository(Context context){
        this.context = context;
    }

    public void addNewItem(final AssignmentTable assignmentTable){
        AsyncTask.execute(() -> AssignmentDatabase.getInstance(context).assignmentDao().insertItem(assignmentTable));
    }

    public LiveData<List<AssignmentTable>> getItems(){
        return AssignmentDatabase.getInstance(context).assignmentDao().getAllItems();
    }


    public void deleteItem(final AssignmentTable assignmentTable){
        AsyncTask.execute(() -> AssignmentDatabase.getInstance(context).assignmentDao().deleteItem(assignmentTable));
    }

}
