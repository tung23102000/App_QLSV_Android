package com.example.projectcuoiky.model;

import java.io.Serializable;

public class Student implements Serializable {
    private int id,idClass;
    private String studentCode,name,date,gender;

    public Student() {
    }

    public Student(int id, String studentCode, String name, String date, String gender,int idClass) {
        this.id = id;
        this.idClass = idClass;
        this.studentCode = studentCode;
        this.name = name;
        this.date = date;
        this.gender = gender;
    }

    public Student(String studentCode, String name, String date, String gender,int idClass) {
        this.idClass = idClass;
        this.studentCode = studentCode;
        this.name = name;
        this.date = date;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
