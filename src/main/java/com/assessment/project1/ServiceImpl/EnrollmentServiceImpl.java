package com.assessment.project1.ServiceImpl;

import com.assessment.project1.DTO.EnrollmentDto;
import com.assessment.project1.Domain.Course;
import com.assessment.project1.Domain.Enrollment;
import com.assessment.project1.Domain.Student;
import com.assessment.project1.Repository.CourseRepository;
import com.assessment.project1.Repository.EnrollmentRepository;
import com.assessment.project1.Repository.StudentRepository;
import com.assessment.project1.Service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    public void saveEnrollment(EnrollmentDto enrollmentDto) {
        Enrollment enrollment = new Enrollment();
        if (enrollmentDto.getStudent() != null) {
            Optional<Student> optionalStudent = studentRepository.findById(enrollmentDto.getStudent().getId());
            optionalStudent.ifPresent(enrollment::setStudent);
        }
        if (enrollmentDto.getCourse() != null) {
            Optional<Course> optionalCourse = courseRepository.findById(enrollmentDto.getCourse().getId());
            optionalCourse.ifPresent(enrollment::setCourse);
        }
        enrollmentRepository.save(enrollment);
    }

}
