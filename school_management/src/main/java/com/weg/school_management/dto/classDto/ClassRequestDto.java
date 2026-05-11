package com.weg.school_management.dto.classDto;

public record ClassRequestDto(
    String name,
    Long courseId,
    Long teacherId
) {

}
