package com.example.projectcuoiky.model;

import java.io.Serializable;

public class Class implements Serializable {
    private int id;
    private String name,room,teacher;

    public Class() {
    }

    public Class(int id, String name, String room, String teacher) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.teacher = teacher;
    }

    public Class(String name, String room, String teacher) {
        this.name = name;
        this.room = room;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
