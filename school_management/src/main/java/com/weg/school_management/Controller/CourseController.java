package com.weg.school_management.Controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weg.school_management.dto.course.CourseRequestDto;
import com.weg.school_management.dto.course.CourseResponseDto;
import com.weg.school_management.service.course.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public CourseResponseDto createCourse(@RequestBody CourseRequestDto requestDto) throws SQLException {
                
        try {

            CourseResponseDto responseDto = courseService.createCourse(requestDto);
            return responseDto;

        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public CourseResponseDto findCourseById (@PathVariable Long id) throws SQLException {

        try {
            
            CourseResponseDto responseDto = courseService.findCourseById(id);

            return responseDto;

        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<CourseResponseDto> findCourseById () throws SQLException {

        try {
            
            List<CourseResponseDto> responseDto = courseService.findAllCourses();

            return responseDto;

        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
