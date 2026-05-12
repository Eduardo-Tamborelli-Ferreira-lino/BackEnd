package com.weg.school_management.dto.student;

import java.time.LocalDate;

public record StudentResponseDto(
    Long id,
    String name,
    String email,
    String registration,
    LocalDate dateBirth
) {

}
