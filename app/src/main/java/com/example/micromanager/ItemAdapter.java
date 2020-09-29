package com.example.micromanager;

import android.app.Application;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<AssignmentTable> assignmentList;

    public ItemAdapter(List<AssignmentTable> items){
        this.assignmentList = items;
    }

    public void setData(List<AssignmentTable> items){
        this.assignmentList=items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Fetching the database table at the current position from database list
        AssignmentTable assignmentTable = assignmentList.get(position);
        //Instantiating the views
        String due = "Due: "+assignmentTable.dueDate;
        String type = assignmentTable.type;
        holder.txtItemName.setText(assignmentTable.name);
        holder.txtItemDate.setText(due);
        holder.txtItemType.setText(type);
        //Setting the checkboxes to their saved state
        if(assignmentTable.isCompleted == true){
            holder.checkBox.setChecked(true);
        }else {
            holder.checkBox.setChecked(false);
        }
        //Fetching current due date and parsing
        Date currentDate = getCurrentDate();
        String dueDateAsString = assignmentTable.dueDate;
        Date dueDateOfCurrentAssignment = parseDate(dueDateAsString);
        //changing the background color programmatically
        if(currentDate.equals(dueDateOfCurrentAssignment)){
            holder.txtItemDate.setText("Due: Today"); //changes due date
            holder.txtItemDate.setTextColor(Color.parseColor("#ff0000");
            holder.itemView.setBackgroundColor(Color.parseColor("#228b22")); // changes color to green
            Log.d("REACHED", "GREEN COLOR");
        }else if(dueDateOfCurrentAssignment.before(currentDate)){
            holder.itemView.setBackgroundColor(Color.parseColor("#8b0000")); // turns the background red
            holder.txtItemDate.setTextColor(Color.parseColor("#ff7a7a")); // turns text bright red
            Log.d("REACHED", "RED COLOR");
        }else{
            holder.txtItemName.setBackgroundColor(Color.parseColor("#191970"));
            holder.txtItemDate.setTextColor(Color.parseColor("#ff0000"));
            holder.txtItemName.setTextSize(40);
            Log.d("REACHED", "NORMAL");
        }
        //Logging fields
        String assignment = "\nName: " + assignmentTable.name + "\nDue Date: " + assignmentTable.dueDate + "\nType: " +
                assignmentTable.type + "\nCompleted: " + assignmentTable.isCompleted;
        Log.d("AT END OF ONBIND", "\n" + assignment);

    }

    public Date parseDate(String date){
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Date dueDate = new Date();
        try{
            dueDate = sdf.parse(date);
        }catch(ParseException exc){
            exc.printStackTrace();
        }
        return dueDate;
    }

    public Date getCurrentDate(){
        Calendar calendar = Calendar.getInstance();
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        String currentDateAsString = sdf.format(calendar.getTime());
        Date currentDate = parseDate(currentDateAsString);
        return currentDate;
    }

    public AssignmentTable getAssignmentTableAt(int position){
        return assignmentList.get(position);
    }


    @Override
    public int getItemCount() {
        return assignmentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtItemName, txtItemDate, txtItemType;
        ImageView delete;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.txt_item_name);
            txtItemDate = itemView.findViewById(R.id.txt_item_date);
            txtItemType = itemView.findViewById(R.id.txt_item_type);
            delete = itemView.findViewById(R.id.img_delete);
            checkBox = itemView.findViewById(R.id.markAsDoneBox);


            delete.setOnClickListener(this::onClick);
            checkBox.setOnClickListener(this::whenChecked);

        }

        public void whenChecked(View view) {
            int position = getAdapterPosition();
            AssignmentTable assignmentTable = assignmentList.get(position);
            ItemRepository repository = new ItemRepository(view.getContext());
            if(checkBox.isChecked()){
                assignmentTable.isCompleted = true;
                checkBox.setChecked(false);
            }else{
                assignmentTable.isCompleted = false;
                checkBox.setChecked(true);
            }
            repository.updateItem(assignmentTable);
        }

        @Override
        public void onClick(View view) {
            //Getting database table at the specified position to delete
            int position = getAdapterPosition();
            AssignmentTable assignmentTable = assignmentList.get(position);
            ItemRepository itemRepository = new ItemRepository(view.getContext());
            //Deleting from the database
            itemRepository.deleteItem(assignmentTable);
            assignmentList.remove(position);
            itemRepository.updateItem(assignmentTable);
            notifyDataSetChanged();
            notifyItemRemoved(position);
            Toast.makeText(view.getContext(),"Deleted!",Toast.LENGTH_SHORT).show();
        }

    }
}
