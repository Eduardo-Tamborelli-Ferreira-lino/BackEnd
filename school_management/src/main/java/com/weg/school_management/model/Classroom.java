package com.weg.school_management.model;

import java.time.LocalDateTime;

public class Classroom {

    private Long id;
    private Long classId;
    private LocalDateTime dateHour;
    
    public Classroom(Long id, Long classId, LocalDateTime dateHour) {
        this.id = id;
        this.classId = classId;
        this.dateHour = dateHour;
    }

    public Classroom(Long classId, LocalDateTime dateHour) {
        this.classId = classId;
        this.dateHour = dateHour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public LocalDateTime getDateHour() {
        return dateHour;
    }

    public void setDateHour(LocalDateTime dateHour) {
        this.dateHour = dateHour;
    }

    
}
