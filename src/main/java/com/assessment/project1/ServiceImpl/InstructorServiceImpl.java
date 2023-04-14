package com.assessment.project1.ServiceImpl;

import com.assessment.project1.DTO.InstructorDto;
import com.assessment.project1.Domain.Instructor;
import com.assessment.project1.Repository.InstructorRepository;
import com.assessment.project1.Service.InstructorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;
    public void addInstructor (InstructorDto instructorDto){
        Instructor instructor = new Instructor();
        instructor.setName(instructorDto.getName());
        instructor.setEmail(instructorDto.getEmail());
        instructor.setDepartment((instructorDto.getDepartment()));
        instructorRepository.save(instructor);
    }
    public InstructorDto findById(long id){
        Optional<Instructor> optionalInstructor = instructorRepository.findById(id);
        if (optionalInstructor.isPresent()){
            Instructor instructor = optionalInstructor.get();
            return new InstructorDto(instructor.getId(),instructor.getName(),instructor.getEmail(),instructor.getDepartment());
        }
        else {
            throw new EntityNotFoundException("Instructor not found with id: "+id);
        }
    }
    public List<InstructorDto> getAllInstructor(){
        List<Instructor> instructors = instructorRepository.findAll();
        List<InstructorDto> instructorDtos = new ArrayList<>();
        for (Instructor instructor : instructors){
            InstructorDto instructorDto = new InstructorDto();
            instructorDto.setName(instructorDto.getName());
            instructorDto.setEmail(instructorDto.getEmail());
            instructorDto.setDepartment(instructorDto.getDepartment());
            instructorDtos.add(instructorDto);
        }
        return instructorDtos;
    }
    public void updateInstructor(InstructorDto instructorDto, long instructorId){
        Optional<Instructor> existingInstructor = instructorRepository.findById(instructorId);
        if (existingInstructor.isPresent()){
            Instructor foundInstructor = existingInstructor.get();
            foundInstructor.setName(instructorDto.getName());
            foundInstructor.setEmail(instructorDto.getEmail());
            foundInstructor.setDepartment(instructorDto.getDepartment());
            instructorRepository.save(foundInstructor);
        }
    }
    public void deleteInstructorById(long instructorId){
        Optional<Instructor> optionalInstructor = instructorRepository.findById(instructorId);
        if (optionalInstructor.isPresent()){
            Instructor instructor = optionalInstructor.get();
            instructorRepository.delete(instructor);
        }
        else{
            throw new RuntimeException("Instructor not found with id: "+instructorId);
        }
    }
}
