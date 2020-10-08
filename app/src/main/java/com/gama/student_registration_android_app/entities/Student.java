package com.gama.student_registration_android_app.entities;

import java.io.Serializable;

// implements Serializable is used to send object as parameters
public class Student implements Serializable {
    private Integer id;
    private String name;
    private String telephone;

    public Student(Integer id, String name, String telephone) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
    }

    public Student() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
