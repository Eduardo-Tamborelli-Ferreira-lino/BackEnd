package com.weg.school_management.repository.course;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.weg.school_management.model.Course;

public interface CourseRepository {

    Course createCourse(Course course) throws SQLException;

    Optional<Course> findCourseById(Long id) throws SQLException;

    List<Course> findAllCourses() throws SQLException;

    Course updateCourse(Course course) throws SQLException;

    void deleteCourse(Long id) throws SQLException;
}
