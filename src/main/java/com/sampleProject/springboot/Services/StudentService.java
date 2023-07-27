package com.sampleProject.springboot.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sampleProject.springboot.Entities.Student;
import com.sampleProject.springboot.Repositories.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student getStudent(int id) {
        Student student = this.studentRepository.getStudentById(id);
        if(student == null)
            throw new IndexOutOfBoundsException();
        else
            return student;
    }

}