package com.weg.school_management.dto.student;

import java.time.LocalDate;

public record StudentRequestDto(    
    String name,
    String email,
    String registration,
    LocalDate dateBirth
) {

}
