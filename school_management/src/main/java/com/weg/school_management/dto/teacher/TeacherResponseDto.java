package com.weg.school_management.dto.teacher;

public record TeacherResponseDto(
    Long id,
    String name,
    String email,
    String subject
) {

}
