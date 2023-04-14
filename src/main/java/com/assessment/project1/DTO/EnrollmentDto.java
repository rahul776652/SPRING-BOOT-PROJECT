package com.assessment.project1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDto {

    private long id;
    private StudentDto student;
    private CourseDto course;
}
