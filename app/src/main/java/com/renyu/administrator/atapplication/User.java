package com.renyu.administrator.atapplication;

import java.io.Serializable;

public class User implements Serializable{
    public String name;
    public String phone;

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return name;
    }
}
