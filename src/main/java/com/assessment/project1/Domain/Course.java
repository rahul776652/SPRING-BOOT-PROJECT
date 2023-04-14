package com.assessment.project1.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "prerequisites")
    private String prerequisites;
    @Column(name = "schedule")
    private String schedule;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;


}
