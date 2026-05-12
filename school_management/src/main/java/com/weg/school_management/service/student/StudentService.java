package com.weg.school_management.service.student;

import java.sql.SQLException;
import java.util.List;

import com.weg.school_management.dto.student.StudentRequestDto;
import com.weg.school_management.dto.student.StudentResponseDto;

public interface StudentService {

    StudentResponseDto createStudent (StudentRequestDto requestDto) throws SQLException;

    StudentResponseDto findStudentById (Long id) throws SQLException;

    List<StudentResponseDto> findAllStudents () throws SQLException;

    StudentResponseDto updateStudent (Long id, StudentRequestDto requestDto) throws SQLException;

    void delete (Long id) throws SQLException;
}
