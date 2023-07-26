package com.sampleProject.springboot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sampleProject.springboot.Entities.Student;
import com.sampleProject.springboot.Services.StudentService;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable("id") int id) {
        Student student = this.studentService.getStudent(id);
        System.out.println(student);
        return student;
    }

}
