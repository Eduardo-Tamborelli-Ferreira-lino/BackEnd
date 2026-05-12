package com.weg.school_management.Controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weg.school_management.dto.teacher.TeacherRequestDto;
import com.weg.school_management.dto.teacher.TeacherResponseDto;
import com.weg.school_management.service.teacher.TeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public TeacherResponseDto createTeacher (@RequestBody TeacherRequestDto requestDto) throws SQLException {

        try{

            TeacherResponseDto responseDto = teacherService.createTeacher(requestDto);

            return responseDto;

        } catch(SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public TeacherResponseDto findTeacherById(@PathVariable Long id) throws SQLException {

        try{

            TeacherResponseDto responseDto = teacherService.findTeacherById(id);

            return responseDto;

        } catch(SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<TeacherResponseDto> findAllTeachers() throws SQLException {

        try{

            List<TeacherResponseDto> responsesDto = teacherService.findAllTeachers();

            return responsesDto;

        } catch(SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public TeacherResponseDto updateTeacher(@PathVariable Long id, @RequestBody TeacherRequestDto requestDto) throws SQLException{

        try{

            TeacherResponseDto responsesDto = teacherService.updateTeacher(id, requestDto);

            return responsesDto;

        } catch(SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Long id) throws SQLException {

        try{

            teacherService.deleteTeacher(id);

        } catch(SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
