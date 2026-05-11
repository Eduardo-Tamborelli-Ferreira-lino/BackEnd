package com.weg.school_management.dto.classroom;

import java.time.LocalDateTime;

public record ClassroomRequestDto(
    Long classId,
    LocalDateTime dateHour
) {

}
