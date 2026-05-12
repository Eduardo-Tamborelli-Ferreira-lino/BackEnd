package com.weg.school_management.mapper;

import org.springframework.stereotype.Component;

import com.weg.school_management.dto.student.StudentRequestDto;
import com.weg.school_management.dto.student.StudentResponseDto;
import com.weg.school_management.model.Student;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequestDto requestDto) {
        return new Student(
            requestDto.name(),
            requestDto.email(),
            requestDto.registration(),
            requestDto.dateBirth()
        );
    }

    public StudentResponseDto forResponseDto(Student student) {
        return new StudentResponseDto(
            student.getId(),
            student.getName(), 
            student.getEmail(), 
            student.getRegistration(), 
            student.getDateBirth()
        );
    }
}
