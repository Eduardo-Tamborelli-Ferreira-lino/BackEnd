package com.weg.school_management.dto.course;

import java.util.List;

public record CourseResponseDto(
    Long id,
    String name,
    String code,
    List<String> teacherNames
) {


}
