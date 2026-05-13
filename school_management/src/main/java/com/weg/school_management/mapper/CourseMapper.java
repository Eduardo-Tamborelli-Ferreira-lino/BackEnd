package com.weg.school_management.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.weg.school_management.dto.course.CourseRequestDto;
import com.weg.school_management.dto.course.CourseResponseDto;
import com.weg.school_management.model.Course;

@Component
public class CourseMapper {

    public Course toEntity(CourseRequestDto requestDto) {

        return new Course(
            requestDto.name(),
            requestDto.code()
        );
    }

    public CourseResponseDto toResponseDto(Course course, List<String> teachers) {

        return new CourseResponseDto(
            course.getId(),
            course.getName(),
            course.getCode(),
            teachers
        );
    }
}
