package com.weg.school_management.dto.course;

import java.util.List;

public record CourseRequestDto(
    String name,
    String code,
    List<Long> teacherIds
) {

}
