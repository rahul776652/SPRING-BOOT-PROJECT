package com.assessment.project1.ServiceImpl;

import com.assessment.project1.DTO.CourseDto;
import com.assessment.project1.Domain.Course;
import com.assessment.project1.Domain.Instructor;
import com.assessment.project1.Repository.CourseRepository;
import com.assessment.project1.Repository.InstructorRepository;
import com.assessment.project1.Service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private InstructorRepository instructorRepository;

    public void addCourse(CourseDto courseDto) {
        Instructor instructor = instructorRepository.findById(courseDto.getInstructor_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid instructor id"));
        Course course = new Course();
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        course.setPrerequisites(courseDto.getPrerequisites());
        course.setSchedule(courseDto.getSchedule());
        course.setInstructor(instructor);
        courseRepository.save(course);
    }
    public CourseDto findById(long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);

        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            return new CourseDto(course.getId(), course.getName(), course.getDescription(), course.getPrerequisites(), course.getSchedule(), course.getInstructor().getId());
        } else {
            throw new EntityNotFoundException("Course not found with id: " + id);
        }
    }
    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDto> courseDtos = new ArrayList<>();
        for (Course course : courses) {
            Instructor instructor = course.getInstructor();
            courseDtos.add(new CourseDto(course.getId(), course.getName(), course.getDescription(),
                    course.getPrerequisites(), course.getSchedule(), instructor.getId()));
        }
        return courseDtos;
    }
    public void updateCourse(CourseDto courseDto, long courseId) {
        Optional<Course> existingCourse = courseRepository.findById(courseId);
        if (existingCourse.isPresent()) {
            Course foundCourse = existingCourse.get();
            foundCourse.setName(courseDto.getName());
            foundCourse.setDescription(courseDto.getDescription());
            foundCourse.setPrerequisites(courseDto.getPrerequisites());
            foundCourse.setSchedule(courseDto.getSchedule());
            Instructor instructor = instructorRepository.findById(courseDto.getInstructor_id()).orElse(null);
            foundCourse.setInstructor(instructor);
            courseRepository.save(foundCourse);
        }
    }

    public void deleteCourseById(long courseId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            courseRepository.delete(course);
        } else {
            throw new RuntimeException("Course not found with id: " + courseId);
        }
    }
}
