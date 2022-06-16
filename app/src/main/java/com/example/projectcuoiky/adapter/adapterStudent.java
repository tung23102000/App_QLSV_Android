package com.example.projectcuoiky.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoiky.R;
import com.example.projectcuoiky.model.Student;

import java.util.ArrayList;
import java.util.List;

public class adapterStudent extends RecyclerView.Adapter<adapterStudent.StudentViewHolder>{
    private List<Student> list;
    private ItemListener itemListener;

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public adapterStudent() {
        list = new ArrayList<>();
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public Student getStudent(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student,parent,false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student st = list.get(position);
        holder.tvStudentCode.setText(st.getStudentCode());
        holder.tvName.setText(st.getName());
        holder.tvDate.setText(st.getDate());
        holder.tvGender.setText(st.getGender());
        if(st.getGender().equalsIgnoreCase("Nam")){
            holder.img.setImageResource(R.drawable.img_3);
        }
        else {
            holder.img.setImageResource(R.drawable.img_4);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvName, tvStudentCode, tvDate,tvGender;
        private ImageView img;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            tvStudentCode=itemView.findViewById(R.id.tvStudentCode);
            tvDate= itemView.findViewById(R.id.tvDate);
            tvGender=itemView.findViewById(R.id.tvGender);
            img=itemView.findViewById(R.id.imgAvatar);
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
