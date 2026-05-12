package com.weg.school_management.mapper;

import org.springframework.stereotype.Component;

import com.weg.school_management.dto.teacher.TeacherRequestDto;
import com.weg.school_management.dto.teacher.TeacherResponseDto;
import com.weg.school_management.model.Teacher;

@Component
public class TeacherMapper {

    public Teacher toEntity(TeacherRequestDto requestDto) {

        return new Teacher(
            requestDto.name(),
            requestDto.email(),
            requestDto.subject()
        );
    }

    public TeacherResponseDto forResponseDto(Teacher teacher) {

        return new TeacherResponseDto(
            teacher.getId(),
            teacher.getName(),
            teacher.getEmail(),
            teacher.getSubject()
        );
    }
}
