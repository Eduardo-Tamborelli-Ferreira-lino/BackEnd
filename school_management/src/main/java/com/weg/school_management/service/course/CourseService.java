package com.weg.school_management.service.course;

import java.sql.SQLException;
import java.util.List;

import com.weg.school_management.dto.course.CourseRequestDto;
import com.weg.school_management.dto.course.CourseResponseDto;

public interface CourseService {

    CourseResponseDto createCourse(CourseRequestDto requestDto) throws SQLException;

    CourseResponseDto findCourseById(Long id) throws SQLException;

    List<CourseResponseDto> findAllCourses() throws SQLException;

    CourseResponseDto updateCourse(Long id, CourseRequestDto requestDto) throws SQLException;

    void deleteCourse(Long id) throws SQLException;
}
