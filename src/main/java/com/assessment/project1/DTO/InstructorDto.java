package com.assessment.project1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorDto {
    private Long id;
    private String name;
    private String email;
    private String department;

}
