package com.assessment.project1.ServiceImpl;

import com.assessment.project1.DTO.StudentDto;
import com.assessment.project1.Domain.Student;
import com.assessment.project1.Repository.StudentRepository;
import com.assessment.project1.Service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void addStudent(StudentDto studentDto){
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setEnrollmentStatus(studentDto.getEnrollmentStatus());
        studentRepository.save(student);
    }
    public StudentDto findById(long id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()){
           Student student = optionalStudent.get();
           return new StudentDto(student.getId(), student.getName(), student.getEmail(), student.getEnrollmentStatus());
        }
        else {
           throw new EntityNotFoundException("Student not found with id: "+id);
        }
    }
    public void updateStudent(StudentDto studentDto, long studentId){
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()){
            Student foundStudent = optionalStudent.get();
            foundStudent.setName(studentDto.getName());
            foundStudent.setEmail(studentDto.getEmail());
            foundStudent.setEnrollmentStatus(studentDto.getEnrollmentStatus());
            studentRepository.save(foundStudent);
        }
    }
    public void deleteStudentById(long studentId){
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()){
            Student student = optionalStudent.get();
            studentRepository.delete(student);
        }
        else {
            throw new RuntimeException("Student not found with id: "+studentId);
        }
    }
}
