package com.example.projectcuoiky;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.projectcuoiky.dal.SQLiteHelper;
import com.example.projectcuoiky.model.Class;
import com.example.projectcuoiky.model.Student;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ActivityStatic extends AppCompatActivity {
   private TextView txt1;
   private SQLiteHelper db;
   private PieChart pieChart;
   long tong=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static);
        txt1 = findViewById(R.id.tvTong);
        pieChart = findViewById(R.id.pieChart);
        db = new SQLiteHelper(this);
        ArrayList<PieEntry> students = new ArrayList<>();

        List<Class> listClass = db.getAllClasses();
        for(int i =0;i<listClass.size();i++){
            int id =listClass.get(i).getId();
            String className = listClass.get(i).getName();
            long numberStudent = db.getCountStudentByClass(id);
            tong+=numberStudent;

            students.add(new PieEntry(Math.toIntExact(numberStudent),className));

        }
        txt1.setText("Tổng số sinh viên: "+Integer.toString(Math.toIntExact(tong)));
        PieDataSet pieDataSet = new PieDataSet(students, "Số sinh viên của từng lớp");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);//ko để mô tả ở góc bên phải mặc định
        pieChart.setCenterText("Students");
        pieChart.setCenterTextColor(Color.BLUE);
        pieChart.animate();
       // txt2.setText(Long.toString(tng));



    }
}