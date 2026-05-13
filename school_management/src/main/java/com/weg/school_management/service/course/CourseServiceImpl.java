package com.weg.school_management.service.course;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.weg.school_management.dto.course.CourseRequestDto;
import com.weg.school_management.dto.course.CourseResponseDto;
import com.weg.school_management.mapper.CourseMapper;
import com.weg.school_management.model.Course;
import com.weg.school_management.model.Teacher;
import com.weg.school_management.repository.classRepo.ClassRepository;
import com.weg.school_management.repository.course.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final ClassRepository classRepository;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper,
            ClassRepository classRepository) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.classRepository = classRepository;
    }

    @Override
    public CourseResponseDto createCourse(CourseRequestDto requestDto) throws SQLException {

        Course course = courseMapper.toEntity(requestDto);

        if (course.getName() == null || course.getCode() == null ||
                course.getName().isBlank() || course.getCode().isBlank()) {
            throw new RuntimeException("Some value is null, pls enter with values.");
        }

        courseRepository.createCourse(course);

        return courseMapper.toResponseDto(course, null);
    }

    @Override
    public CourseResponseDto findCourseById(Long id) throws SQLException {

        Course course = courseRepository.findCourseById(id).orElseThrow(() -> new RuntimeException("Course not found"));

        List<Teacher> teachers = classRepository.findAllTeachers(id);

        List<String> teacherNames = new ArrayList<>();

        for (Teacher teacher : teachers) {
            teacherNames.add(teacher.getName());
        }

        return courseMapper.toResponseDto(course, teacherNames);
    }

    @Override
    public List<CourseResponseDto> findAllCourses() throws SQLException {

        List<Course> courses = new ArrayList<>();

        List<CourseResponseDto> coursesDto = new ArrayList<>();

        courses = courseRepository.findAllCourses();

        if (courses == null || courses.isEmpty()) {
            throw new RuntimeException(
                    "Don't have any courses in the system, enter with a courses and after do this action");
        }

        for (Course course : courses) {
            
            List<Teacher> teachers = classRepository.findAllTeachers(course.getId());

            List<String> teacherNames = teachers.stream().map(Teacher :: getName).collect(Collectors.toList());

            coursesDto.add(courseMapper.toResponseDto(course, teacherNames));
        }

        return coursesDto;
    }

    @Override
    public CourseResponseDto updateCourse(Long id, CourseRequestDto requestDto) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCourse'");
    }

    @Override
    public void deleteCourse(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCourse'");
    }

}
