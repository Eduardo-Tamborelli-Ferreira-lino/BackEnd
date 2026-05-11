package com.weg.school_management.dto.student;

import java.time.LocalDateTime;

public record StudentRequestDto(    
    String name,
    String email,
    String registration,
    LocalDateTime dateBirth
) {

}
