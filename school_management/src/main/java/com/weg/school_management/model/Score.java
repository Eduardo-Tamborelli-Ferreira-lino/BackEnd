package com.weg.school_management.model;

public class Score {

    private Long id;
    private Long studentId;
    private Long classroomId;

    public Score(Long id, Long studentId, Long classroomId) {
        this.id = id;
        this.studentId = studentId;
        this.classroomId = classroomId;
    }

    public Score(Long studentId, Long classroomId) {
        this.studentId = studentId;
        this.classroomId = classroomId;
    }

    public Score() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    
}
