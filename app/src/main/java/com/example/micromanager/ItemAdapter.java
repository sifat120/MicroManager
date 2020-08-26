package com.example.micromanager;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        String type = "Type: "+assignmentTable.type;
        holder.txtItemName.setText(assignmentTable.name);
        holder.txtItemDate.setText(due);
        holder.txtItemType.setText(type);


    }

    @Override
    public int getItemCount() {
        return assignmentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtItemName;
        TextView txtItemDate;
        TextView txtItemType;
        ImageView delete;
        ImageView priorityLabel;
        TextView menu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.txt_item_name);
            txtItemDate = itemView.findViewById(R.id.txt_item_date);
            txtItemType = itemView.findViewById(R.id.txt_item_type);
            delete = itemView.findViewById(R.id.img_delete);
            menu = itemView.findViewById(R.id.textViewOptions);
            priorityLabel = itemView.findViewById(R.id.priorityLabel);

            delete.setOnClickListener(this::onClick);

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
                        case R.id.markAsDone:
                            //change background color
                            if(checker == 0) {
                                itemView.setBackgroundColor(Color.HSVToColor(new float[]{109, 100, 50}));
                                checker = 1;
                            }
                            else{
                                itemView.setBackgroundColor(Color.TRANSPARENT);
                                checker = 0;
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
