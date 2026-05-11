package com.weg.school_management.model;

public class Class {

    private Long id;
    private String name;
    private Long courseId;
    private Long teacherId;

    public Class(Long id, String name, Long courseId, Long teacherId) {
        this.id = id;
        this.name = name;
        this.courseId = courseId;
        this.teacherId = teacherId;
    }

    public Class(String name, Long courseId, Long teacherId) {
        this.name = name;
        this.courseId = courseId;
        this.teacherId = teacherId;
    }

    public Class() {
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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    
}
