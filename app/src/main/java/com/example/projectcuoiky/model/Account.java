package com.example.projectcuoiky.model;

import java.io.Serializable;

public class Account implements Serializable {
    private int id;
    private String name,gmail,username, password;

    public Account() {
    }

    public Account(int id, String name, String gmail, String password) {
        this.id = id;
        this.name = name;
        this.gmail = gmail;
        this.password = password;
    }

    public Account(int id, String name, String gmail, String username, String password) {
        this.id = id;
        this.name = name;
        this.gmail = gmail;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account(String name, String gmail, String username) {
        this.name = name;
        this.gmail = gmail;
        this.username = username;
    }

    public Account(String name, String gmail, String username, String password) {
        this.name = name;
        this.gmail = gmail;
        this.username = username;
        this.password = password;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
