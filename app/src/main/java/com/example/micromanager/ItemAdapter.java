package com.example.micromanager;

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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    int checker=0;

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
        AssignmentTable assignmentTable = assignmentList.get(position);
        String due = "Due: "+assignmentTable.dueDate;
        String type = assignmentTable.type;
        holder.txtItemName.setText(assignmentTable.name);
        holder.txtItemDate.setText(due);
        holder.txtItemType.setText(type);


    }

    @Override
    public int getItemCount() {
        return assignmentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtItemName, txtItemDate, txtItemType, menu;
        ImageView delete, priorityLabel;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.txt_item_name);
            txtItemDate = itemView.findViewById(R.id.txt_item_date);
            txtItemType = itemView.findViewById(R.id.txt_item_type);
            delete = itemView.findViewById(R.id.img_delete);
            menu = itemView.findViewById(R.id.textViewOptions);
            checkBox = itemView.findViewById(R.id.markAsDoneBox);
            priorityLabel = itemView.findViewById(R.id.priorityLabel);

            delete.setOnClickListener(this::onClick);
            checkBox.setOnClickListener(this::whenChecked);

            menu.setOnClickListener(view -> {
                int position = getAdapterPosition();
                AssignmentTable assignmentTable = assignmentList.get(position);
                ItemRepository itemRepository = new ItemRepository(view.getContext());
                //creating a popup menu
                PopupMenu popup = new PopupMenu(view.getContext(),menu);
                //inflating menu from xml resource
                popup.inflate(R.menu.options_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.priority:
                            assignmentTable.isHighPriority = true;
                            if(priorityLabel.getVisibility() == View.INVISIBLE) {
                                priorityLabel.setVisibility(View.VISIBLE);
                            }
                            else{
                                priorityLabel.setVisibility(View.INVISIBLE);
                            }
                            break;
                        default:
                            break;
                    }
                    return false;
                });
                //displaying the popup
                popup.show();

            });
        }

        public void whenChecked(View view) {
            int position = getAdapterPosition();
            AssignmentTable assignmentTable = assignmentList.get(position);
            Log.d("TABLE", assignmentTable.name + " " + assignmentTable.dueDate + " "+ assignmentTable.type + " " + assignmentTable.isCompleted);
            checkBox.setChecked(true);
            assignmentTable.isCompleted = true;
            Log.d("TABLE", assignmentTable.name + " " + assignmentTable.dueDate + " "+ assignmentTable.type + " " + assignmentTable.isCompleted);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            AssignmentTable assignmentTable = assignmentList.get(position);
            ItemRepository itemRepository = new ItemRepository(view.getContext());
            itemRepository.deleteItem(assignmentTable);
            Toast.makeText(view.getContext(),"Deleted!",Toast.LENGTH_SHORT).show();
        }
    }
}
