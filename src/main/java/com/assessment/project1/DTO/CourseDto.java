package com.assessment.project1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private Long id;
    private String name;
    private String description;
    private String prerequisites;
    private String schedule;
    private long instructor_id;



}
