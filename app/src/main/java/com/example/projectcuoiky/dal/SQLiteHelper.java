package com.example.projectcuoiky.dal;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectcuoiky.model.Account;
import com.example.projectcuoiky.model.Class;
import com.example.projectcuoiky.model.Student;
import com.example.projectcuoiky.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="studentManagement.db";
    private static int DATABASE_VERSION=1;
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
         String sql = "CREATE TABLE classes(" +
                 "idClass INTEGER PRIMARY KEY AUTOINCREMENT," +
                 "tenLop TEXT, phongHoc TEXT, giaoVien TEXT)";
         String sql1 = "CREATE TABLE subjects(" +
                 "idSubject INTEGER PRIMARY KEY AUTOINCREMENT," +
                 "tenMon TEXT,"+
                 "soTinChi TEXT," +
                 "phongHoc TEXT, giaoVien TEXT)";
         String sql2 ="CREATE TABLE students(" +
                 "idStudent INTEGER PRIMARY KEY AUTOINCREMENT," +
                 "maSV TEXT,ten TEXT,ngaySinh TEXT,gioiTinh TEXT," +
                 "idClass INTEGER, FOREIGN KEY (idClass) REFERENCES classes(idClass))";
         String sql3 = "CREATE TABLE users(" +
                 "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                 "name TEXT, gmail TEXT," +
                 "username TEXT, password TEXT)";
         sqLiteDatabase.execSQL(sql);
         sqLiteDatabase.execSQL(sql1);
         sqLiteDatabase.execSQL(sql2);
         sqLiteDatabase.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    //lấy ra tất cả các lớp
    public List<Class> getAllClasses(){
        List<Class> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        Cursor cursor = st.query("classes",null,null,null,null,null,null);
        while (cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String tenLop = cursor.getString(1);
            String phongHoc = cursor.getString(2);
            String giaoVien = cursor.getString(3);
            list.add(new Class(id,tenLop,phongHoc,giaoVien));
        }
        return list;

    }
    //thêm lớp
    public void addClass(Class c){
        String sql = "INSERT INTO classes(tenLop,phongHoc,giaoVien)" +
                "VALUES(?,?,?)";
        String[] agrs={c.getName(), c.getRoom(), c.getTeacher()};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql,agrs);
    }
    //sửa lớp
    public void updateClass(Class c){
        String sql = "UPDATE classes SET tenLop = ?, phongHoc = ?, giaoVien = ?" +
                "WHERE idClass = ?";
        String[] args = {c.getName(),c.getRoom(), c.getTeacher(),Integer.toString(c.getId())};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql,args);
    }
    //xóa lớp
    public void deleteClass(int id){
        String sql ="DELETE FROM classes WHERE idClass = ?";
        String[] args ={Integer.toString(id)};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql,args);
    }
    //xóa các sinh viên của lớp học đã bị xóa
    public void deleteClassStudent(int id){
        String sql="DELETE FROM students WHERE idClass = ?";
        String[] args={Integer.toString(id)};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql,args);
    }


    //lay ra tat ca sinh vien cua 1 lop
    public List<Student> getAllStudentByClass(int idClass){
        List<Student> listStudents = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "ten DESC";
        String whereClause="idClass = ?";
        String[] whereArgs={Integer.toString(idClass)};
        Cursor cursor = st.query("students",null,whereClause,whereArgs,null,null,order);
        while (cursor!=null&&cursor.moveToNext()){
            int idStudent = cursor.getInt(0);
            String maSV = cursor.getString(1);
            String ten = cursor.getString(2);
            String ngaySinh = cursor.getString(3);
            String gioiTinh= cursor.getString(4);
            listStudents.add(new Student(idStudent,maSV,ten,ngaySinh,gioiTinh,idClass));


        }

        return listStudents;
    }
    //thêm sv
    public void addStudent(Student s){
        String sql ="INSERT INTO students(maSV,ten,ngaySinh,gioiTinh,idClass)" +
                "VALUES(?,?,?,?,?)";
        String[] args={s.getStudentCode(),s.getName(),s.getDate(),s.getGender(),Integer.toString(s.getIdClass())};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql,args);

    }
    //sua Sv
    public void updateStudent(Student s){
        String sql ="UPDATE students SET maSV= ?," +
                "ten=?,ngaySinh=?,gioiTinh=?,idClass=? WHERE idStudent= ?";
        String[] args={s.getStudentCode(),s.getName(),s.getDate(),s.getGender(),Integer.toString(s.getIdClass()),Integer.toString(s.getId())};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql,args);
    }
    //xoa Sv
    public void deleteStudent(int id){
        String sql = "DELETE FROM students WHERE idStudent = ?";
        String[] args={Integer.toString(id)};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql,args);
    }

    //kiểm tra username
    public boolean checkUsername(String username){
        String sql = "SELECT * FROM users WHERE username =? ";
        String[] args = {username};
        SQLiteDatabase st = getWritableDatabase();
        Cursor cursor = st.rawQuery(sql,args);
        if(cursor.getCount()>0){
            return true;
        }
        else  return false;
    }


    //kiểm tra username và password
    public boolean checkUsernamePassword(String username, String password){
        String sql = "SELECT * FROM users WHERE username = ? and password = ?";
        String[] args = {username,password};
        SQLiteDatabase st = getWritableDatabase();
      Cursor cursor=  st.rawQuery(sql,args);
      if(cursor.getCount()>0){
          return true;
      }
      else return false;
    }
    //add user
    public void addUser(Account account){
        String sql = "INSERT INTO users(name,gmail,username,password)" +
                "VALUES(?,?,?,?)";
        String[] args = {account.getName(),account.getGmail(),account.getUsername(),account.getPassword()};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql,args);
    }
    //update account
    public void updateAccount(Account account){
        String sql = "UPDATE users SET name= ?, gmail= ?, username =  ?,password = ?" +
                "WHERE id = ?";
        String[] args= {account.getName(),account.getGmail(),account.getUsername(),account.getPassword(),Integer.toString(account.getId())};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql,args);
    }
    //get account
    public List<Account> getUser(){
        List<Account> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        Cursor cursor = st.query("users",null,null,null,null,null,null);
        while (cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String gmail = cursor.getString(2);
            String username = cursor.getString(3);
            String password = cursor.getString(4);
            list.add(new Account(id,ten,gmail,username,password));
        }
        return list;
    }

    //đổi mật khẩu
    public void changePassword(Account account){
        String sql ="UPDATE users SET password = ? WHERE username = ?";
        String[] args ={account.getPassword(),account.getUsername()};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql,args);
    }

    //lay ra tat ca mon hoc
    public List<Subject> getAllSubjects(){
        List<Subject> subjectList = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        Cursor cursor = st.query("subjects",null,null,null,null,null,null);
        while (cursor!=null&&cursor.moveToNext()){
            int idSubject = cursor.getInt(0);
            String ten = cursor.getString(1);
            String soTin = cursor.getString(2);
            String phong = cursor.getString(3);
            String giaoVien = cursor.getString(4);
            subjectList.add(new Subject(idSubject,ten,soTin,phong,giaoVien));

        }
        return subjectList;
    }
    //them mon hoc
    public void addSubject(Subject s){
        String sql = "INSERT INTO subjects(tenMon,soTinChi,phongHoc,giaoVien)" +
                "VALUES(?,?,?,?)";
        String[] args = {s.getTenMon(),s.getSoTin(),s.getPhong(),s.getGiaoVien()};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql,args);
    }
    //sua mon hoc
    public void updateSubject(Subject subject){
        String sql = "UPDATE subjects SET tenMon= ?, soTinChi= ?, phongHoc =  ?,giaoVien = ?" +
                "WHERE idSubject = ?";
        String[] args= {subject.getTenMon(),subject.getSoTin(),subject.getPhong(),subject.getGiaoVien(),Integer.toString(subject.getId())};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql,args);
    }
    //xoa mon hoc
    public void deleteSubject(int id){
        String sql = "DELETE FROM subjects WHERE idSubject = ?";
        String[] args ={Integer.toString(id)};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql,args);
    }
    //đếm số sinh viên từng lớp
    public long getCountStudentByClass(int idClass){
        String whereClause="idClass = ?";
        String[] whereArgs={Integer.toString(idClass)};
        SQLiteDatabase db = getReadableDatabase();
       long count= DatabaseUtils.queryNumEntries(db,"students",whereClause,whereArgs);
        return count;
    }
    //lấy ra thông tin account sau khi check user, pass
    public Account login(String username,String password){
        Account account =null;
        String sql = "SELECT * FROM users WHERE username = ? and password = ?";
        String[] args = {username,password};
        SQLiteDatabase st = getWritableDatabase();
        Cursor cursor=  st.rawQuery(sql,args);
        if(cursor.moveToFirst()){
             account = new Account();
             account.setId(cursor.getInt(0));
            account.setName(cursor.getString(1));
            account.setGmail(cursor.getString(2));
            account.setUsername(cursor.getString(3));


        }
        return account;
    }
    public List<Class> searchByClass(String key){
        List<Class> list = new ArrayList<>();
        String whereClause = "tenLop like ?";
        String[] whereArgs = {"%"+key+"%"};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("classes",null,whereClause,whereArgs,null,null,null);
        while (rs!=null&&rs.moveToNext()){
            int id = rs.getInt(0);
            String tenLop = rs.getString(1);
            String phong = rs.getString(2);
            String giaoVien = rs.getString(3);
            list.add(new Class(id,tenLop,phong,giaoVien));

        }
        return  list;
    }
    public List<Student> searchStudentByClass(int idClass,String key){
        List<Student> listStudents = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();

        String whereClause="idClass = ? and maSV like ? or ten like ?";
        String[] whereArgs={Integer.toString(idClass), "%"+key+"%", "%"+key+"%"};
        Cursor cursor = st.query("students",null,whereClause,whereArgs,null,null,null);
        while (cursor!=null&&cursor.moveToNext()){
            int idStudent = cursor.getInt(0);
            String maSV = cursor.getString(1);
            String ten = cursor.getString(2);
            String ngaySinh = cursor.getString(3);
            String gioiTinh= cursor.getString(4);
            listStudents.add(new Student(idStudent,maSV,ten,ngaySinh,gioiTinh,idClass));


        }

        return listStudents;
    }
    public List<Subject> searchSubject(String key){
        List<Subject> subjectList = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String whereClause = "tenMon like ?";
        String[] whereArgs = {"%"+key+"%"};
        Cursor rs = st.query("subjects",null,whereClause,whereArgs,null,null,null);
        while (rs!=null&&rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String soTin =rs.getString(2);
            String phongHoc = rs.getString(3);
            String giaoVien = rs.getString(4);
            subjectList.add(new Subject(id,ten,soTin,phongHoc,giaoVien));
        }
        return subjectList;
    }

}
