package com.weg.school_management.service.student;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.weg.school_management.dto.student.StudentRequestDto;
import com.weg.school_management.dto.student.StudentResponseDto;
import com.weg.school_management.mapper.StudentMapper;
import com.weg.school_management.model.Student;
import com.weg.school_management.repository.student.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentMapper studentMapper, StudentRepository studentRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentResponseDto createStudent(StudentRequestDto requestDto) throws SQLException {
        
        Student student = studentMapper.toEntity(requestDto);

        List<Student> students = studentRepository.findAllStudents();

        if (student.getName() == null || student.getEmail() == null || student.getRegistration() == null || student.getDateBirth() == null ||
            student.getName().isBlank() || student.getEmail().isBlank() || student.getRegistration().isBlank()) {
            throw new RuntimeException("Some value is null, enter with all values.");
        }

        for (Student findStudent : students) {
            if (findStudent.getEmail() == (student.getEmail())) {
                throw new RuntimeException(findStudent.getEmail() + ", already exist in the system.");
            }
        }

        studentRepository.createStudent(student);


        return studentMapper.forResponseDto(student);
    }

    @Override
    public StudentResponseDto findStudentById(Long id) throws SQLException {

        if (id == null) {
            throw new RuntimeException("You have to enter with a id value.");
        }

        Student student = studentRepository.findStudentById(id).
            orElseThrow(() -> new RuntimeException("Student not found in the system"));

        return studentMapper.forResponseDto(student);
    }

    @Override
    public List<StudentResponseDto> findAllStudents() throws SQLException {

        List<Student> students = studentRepository.findAllStudents();

        if (students == null || students.isEmpty()) {
            throw new RuntimeException("Don't have any student in the system, enter with a student and after do this action");
        }

        return students.stream().map(studentMapper :: forResponseDto).toList();
    }

    @Override
    public StudentResponseDto updateStudent(Long id, StudentRequestDto requestDto) throws SQLException {

        Student student = studentMapper.toEntity(requestDto);

        List<Student> students = studentRepository.findAllStudents();

        if (id == null || student.getName() == null || student.getEmail() == null || student.getRegistration() == null || student.getDateBirth() == null ||
            student.getName().isBlank() || student.getEmail().isBlank() || student.getRegistration().isBlank()) {
            throw new RuntimeException("Some value is null, enter with all values.");
        }

        for (Student findStudent : students) {
            if (findStudent.getEmail() == (student.getEmail())) {
                throw new RuntimeException(findStudent.getEmail() + ", already exist in the system");
            }
        }

        student.setId(id);

        studentRepository.updateStudent(student);

        return studentMapper.forResponseDto(student);
    }

    @Override
    public void delete(Long id) throws SQLException {

        if (id == null) {
            throw new RuntimeException("You have to enter with a id value.");
        }

        studentRepository.delete(id);
    }

}
