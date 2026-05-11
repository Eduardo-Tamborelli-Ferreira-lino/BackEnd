package com.weg.school_management.model;

public class Teacher {

    private Long id;
    private String name;
    private String email;
    private String disccipline;
    
    public Teacher(Long id, String name, String email, String disccipline) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.disccipline = disccipline;
    }

    public Teacher(String name, String email, String disccipline) {
        this.name = name;
        this.email = email;
        this.disccipline = disccipline;
    }

    public Teacher() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisccipline() {
        return disccipline;
    }

    public void setDisccipline(String disccipline) {
        this.disccipline = disccipline;
    }

    
}
