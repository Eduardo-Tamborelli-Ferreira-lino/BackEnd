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

import com.weg.school_management.dto.student.StudentRequestDto;
import com.weg.school_management.dto.student.StudentResponseDto;
import com.weg.school_management.service.student.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentResponseDto createStudent(@RequestBody StudentRequestDto requestDto) throws SQLException {

        try {

            StudentResponseDto responseDto = studentService.createStudent(requestDto);
            return responseDto;

        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public StudentResponseDto findStudentById (@PathVariable Long id) throws SQLException {

        try {
            
            StudentResponseDto responseDto = studentService.findStudentById(id);

            return responseDto;

        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<StudentResponseDto> findAllStudents() throws SQLException {

        try {
            
            List<StudentResponseDto> responseDtos = studentService.findAllStudents();

            return responseDtos;

        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public StudentResponseDto updateStudent(@PathVariable Long id, @RequestBody StudentRequestDto requestDto) throws SQLException{

        try{

            StudentResponseDto responseDto = studentService.updateStudent(id, requestDto);

            return responseDto;

        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) throws SQLException {

        try{

            studentService.delete(id);

        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
