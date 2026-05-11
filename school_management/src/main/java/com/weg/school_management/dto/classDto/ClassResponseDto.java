package com.weg.school_management.dto.classDto;

public record ClassResponseDto(
    Long id,
    String name,
    Long courseId,
    Long teacherId
) {

}
