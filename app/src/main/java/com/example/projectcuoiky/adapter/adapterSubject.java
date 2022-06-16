package com.example.projectcuoiky.adapter;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoiky.ActivitySubject;
import com.example.projectcuoiky.R;
import com.example.projectcuoiky.UpdateSubjectActivity;
import com.example.projectcuoiky.dal.SQLiteHelper;
import com.example.projectcuoiky.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class adapterSubject extends RecyclerView.Adapter<adapterSubject.subjectViewHolder>{
    private List<Subject> subjectList;
    private ActivitySubject context;
    private ItemListener itemListener;

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }
    public adapterSubject(ActivitySubject context) {
        subjectList = new ArrayList<>();
        this.context = context;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
        notifyDataSetChanged();
    }
   public Subject getSubject(int position){
        return subjectList.get(position);
   }
    @NonNull
    @Override
    public subjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject,parent,false);

        return new subjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull subjectViewHolder holder, int position) {
         Subject s = subjectList.get(position);
         holder.ten.setText(s.getTenMon());
         holder.soTin.setText(s.getSoTin());
         holder.phong.setText(s.getPhong());
         holder.giaoVien.setText(s.getGiaoVien());
         holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(context, UpdateSubjectActivity.class);
                 intent.putExtra("subject",s);
                 context.startActivity(intent);
             }
         });
         holder.btnDelete.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 AlertDialog.Builder builder = new AlertDialog.Builder(context);
                 builder.setTitle("Thông báo xóa");
                 builder.setMessage("Bạn có muốn xóa môn học "+ s.getTenMon()+" này không?");
                 builder.setIcon(R.drawable.ic_baseline_clear_24);
                 builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         int id = s.getId();
                         SQLiteHelper db = new SQLiteHelper(view.getContext());
                         db.deleteSubject(id);
                         subjectList.remove(position);
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
        return subjectList.size();
    }

    public class subjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView ten,soTin,phong,giaoVien;
        private ImageButton btnUpdate,btnDelete;
        public subjectViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tvNameSub);
            soTin = itemView.findViewById(R.id.tvNumber);
            phong = itemView.findViewById(R.id.tvRoomSub);
            giaoVien=itemView.findViewById(R.id.tvTeacherSub);
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
