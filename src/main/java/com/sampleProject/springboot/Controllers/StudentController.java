package com.sampleProject.springboot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
        try {
            Student student = this.studentService.getStudent(id);
            if (student == null)
                throw new IndexOutOfBoundsException();
            return ResponseEntity.ok(student);
        } catch (IndexOutOfBoundsException ex) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

}
