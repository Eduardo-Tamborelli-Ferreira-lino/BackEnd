package com.weg.school_management.service.teacher;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.weg.school_management.dto.teacher.TeacherRequestDto;
import com.weg.school_management.dto.teacher.TeacherResponseDto;
import com.weg.school_management.mapper.TeacherMapper;
import com.weg.school_management.model.Teacher;
import com.weg.school_management.repository.teacher.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService{

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public TeacherResponseDto createTeacher(TeacherRequestDto requestDto) throws SQLException {

        Teacher teacher = teacherMapper.toEntity(requestDto);

        List<Teacher> teachers = teacherRepository.findAllTeachers();

        if (teacher.getName() == null || teacher.getEmail() == null || teacher.getSubject() == null ||
        teacher.getName().isBlank() || teacher.getEmail().isBlank() || teacher.getSubject().isBlank()) {
            
            throw new RuntimeException("Some value is null, pls enter with values.");
        }

        for (Teacher findTeacher : teachers) {
            if (findTeacher.getEmail() == teacher.getEmail()) {
                throw new RuntimeException("The email already exists in the system");
            }
        }

        teacherRepository.createTeacher(teacher);

        return teacherMapper.forResponseDto(teacher);
    }

    @Override
    public TeacherResponseDto findTeacherById(Long id) throws SQLException {

        if (id == null) {
            throw new RuntimeException("Id can't be null, insert a correct value.");
        }

        Teacher teacher = teacherRepository.findTeacherById(id).
            orElseThrow(() -> new RuntimeException("This ID don't exists in the system"));

        return teacherMapper.forResponseDto(teacher);
    }

    @Override
    public List<TeacherResponseDto> findAllTeachers() throws SQLException {

        List<Teacher> teachers = teacherRepository.findAllTeachers();

        if (teachers == null || teachers.isEmpty()) {
            throw new RuntimeException("Enter a teacher in the system, after this, try found teachers");
        }

        return teachers.stream().map(teacherMapper :: forResponseDto).toList();
    }

    @Override
    public TeacherResponseDto updateTeacher(Long id, TeacherRequestDto requestDto) throws SQLException {

        Teacher teacher = teacherMapper.toEntity(requestDto);

        List<Teacher> teachers = teacherRepository.findAllTeachers();

        if (teacher.getName() == null || teacher.getEmail() == null || teacher.getSubject() == null ||
        teacher.getName().isBlank() || teacher.getEmail().isBlank() || teacher.getSubject().isBlank()) {
            
            throw new RuntimeException("Some value is null, pls enter with values.");
        }

        for (Teacher findTeacher : teachers) {
            if (findTeacher.getEmail() == teacher.getEmail()) {
                throw new RuntimeException("The email already exists in the system");
            }
        }

        teacher.setId(id);

        teacherRepository.updateTeacher(teacher);

        return teacherMapper.forResponseDto(teacher);

    }

    @Override
    public void deleteTeacher(Long id) throws SQLException {
 
        if (id == null) {
            throw new RuntimeException("Id can't be null, insert a correct value.");
        }

        teacherRepository.deleteTeacher(id);
    }

}
