package com.weg.school_management.service.teacher;

import java.sql.SQLException;
import java.util.List;

import com.weg.school_management.dto.teacher.TeacherRequestDto;
import com.weg.school_management.dto.teacher.TeacherResponseDto;

public interface TeacherService {

    TeacherResponseDto createTeacher(TeacherRequestDto requestDto) throws SQLException;

    TeacherResponseDto findTeacherById(Long id) throws SQLException;

    List<TeacherResponseDto> findAllTeachers() throws SQLException;

    TeacherResponseDto updateTeacher(Long id, TeacherRequestDto requestDto) throws SQLException;

    void deleteTeacher (Long id) throws SQLException;
}
