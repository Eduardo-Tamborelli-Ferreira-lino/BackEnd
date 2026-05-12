package com.weg.school_management.mapper;

import org.springframework.stereotype.Component;

import com.weg.school_management.dto.course.CourseRequestDto;
import com.weg.school_management.dto.course.CourseResponseDto;
import com.weg.school_management.model.Course;

@Component
public class CourseMapper {

    public Course toEntity(CourseRequestDto requestDto) {

        return new Course(
            requestDto.name(),
            requestDto.code(),
            requestDto.teacherIds()
        );
    }

    public CourseResponseDto toResponseDto(Course course) {

        return new CourseResponseDto(
            course.getId(),
            course.getName(),
            course.getCode(),
            course.getTeacherNames()
        );
    }
}
