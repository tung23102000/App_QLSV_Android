package com.example.projectcuoiky.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoiky.ActivityClass;
import com.example.projectcuoiky.ActivityStudent;
import com.example.projectcuoiky.AddClassActivity;
import com.example.projectcuoiky.AddStudentActivity;
import com.example.projectcuoiky.R;
import com.example.projectcuoiky.UpdateClassActivity;
import com.example.projectcuoiky.dal.SQLiteHelper;
import com.example.projectcuoiky.model.Class;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class adapterClass extends RecyclerView.Adapter<adapterClass.ClassViewHolder> {
    private List<Class> list;
    private ActivityClass context;
    private ItemListener itemListener;
    public adapterClass() {
    }

    public adapterClass(ActivityClass context) {
        list = new ArrayList<>();
        this.context=context;
    }
    public void setItemListener(adapterClass.ItemListener itemListener) {
        this.itemListener = itemListener;
    }
    public List<Class> getList() {
        return list;
    }

    public void setList(List<Class> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public Class getClass(int position){
        return list.get(position);
    }
    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class,parent,false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        Class c = list.get(position);

        holder.tvName.setText(c.getName());
        holder.tvRoom.setText(c.getRoom());
        holder.tvTeacher.setText(c.getTeacher());
        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityStudent.class);

                int class_id = c.getId();
                intent.putExtra("idClass",class_id);

                context.startActivity(intent);


            }
        });
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateClassActivity.class);
                intent.putExtra("class",c);
                context.startActivity(intent);


            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo xóa");
                builder.setMessage("Bạn có muốn xóa lớp học "+ c.getName()+" này không?");
                builder.setIcon(R.drawable.ic_baseline_clear_24);
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int id = c.getId();
                        SQLiteHelper db = new SQLiteHelper(view.getContext());
                        db.deleteClass(id);
                        db.deleteClassStudent(id);
                        list.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       private TextView tvName,tvRoom,tvTeacher;
       private ImageButton btnView,btnUpdate,btnDelete;
        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            tvRoom=itemView.findViewById(R.id.tvRoom);
            tvTeacher=itemView.findViewById(R.id.tvTeacher);
            btnView= itemView.findViewById(R.id.btnView);
            btnUpdate=itemView.findViewById(R.id.btnUpdate);
            btnDelete=itemView.findViewById(R.id.btnDelete);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemListener!=null){
                itemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }
    public interface ItemListener{
        void onItemClick(View view,int position);
    }
}
