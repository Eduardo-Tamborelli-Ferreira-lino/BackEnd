package com.weg.school_management.model;

import java.util.List;

public class Course {

    private Long id;
    private String name;
    private String code;
    private List<Long> teacherIds;
    private List<String> teacherNames;

    public Course(Long id, String name, String code, List<Long> teacherIds, List<String> teacherNames) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.teacherIds = teacherIds;
        this.teacherNames = teacherNames;
    }

    public Course(String name, String code, List<Long> teacherIds, List<String> teacherNames) {
        this.name = name;
        this.code = code;
        this.teacherIds = teacherIds;
        this.teacherNames = teacherNames;
    }

    public Course(String name, String code, List<Long> teacherIds) {
        this.name = name;
        this.code = code;
        this.teacherIds = teacherIds;
    }

    public Course(Long id, String name, String code, List<String> teacherNames) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.teacherNames = teacherNames;
    }

    public Course(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Long> getTeacherIds() {
        return teacherIds;
    }

    public void setTeacherIds(List<Long> teacherIds) {
        this.teacherIds = teacherIds;
    }

    public List<String> getTeacherNames() {
        return teacherNames;
    }

    public void setTeacherNames(List<String> teacherNames) {
        this.teacherNames = teacherNames;
    }

}
