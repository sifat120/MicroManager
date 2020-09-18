package com.example.micromanager;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AssignmentViewModel extends AndroidViewModel {

    private ItemRepository itemRepository;
    private LiveData<List<AssignmentTable>> items;

    public AssignmentViewModel(@NonNull Application application) {
        super(application);
        itemRepository = new ItemRepository(application.getApplicationContext());
        items = itemRepository.getItems();
    }

    public void insertNewItem(AssignmentTable assignmentTable){
        itemRepository.addNewItem(assignmentTable);
    }


    public LiveData<List<AssignmentTable>> getItems(){
        return items;
    }

    public void updateAssignment(AssignmentTable assignmentTable){
        itemRepository.updateItem(assignmentTable);
    }

    public void deleteAllCompletedAssignments(){
        itemRepository.deleteCompletedAssignments();
    }

    public void deleteOverdueAssignments(){
        itemRepository.deleteOverdueAssignments();
    }

    public void deleteItem(AssignmentTable assignmentTable){
        itemRepository.deleteItem(assignmentTable);
    }
}
