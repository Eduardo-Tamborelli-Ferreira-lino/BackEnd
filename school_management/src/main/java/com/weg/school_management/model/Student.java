package com.weg.school_management.model;

import java.time.LocalDate;

public class Student {

    private Long id;
    private String name;
    private String email;
    private String registration;
    private LocalDate dateBirth;
    
    public Student(Long id, String name, String email, String registration, LocalDate dateBirth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.registration = registration;
        this.dateBirth = dateBirth;
    }

    public Student(String name, String email, String registration, LocalDate dateBirth) {
        this.name = name;
        this.email = email;
        this.registration = registration;
        this.dateBirth = dateBirth;
    }

    public Student() {
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

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    
}
