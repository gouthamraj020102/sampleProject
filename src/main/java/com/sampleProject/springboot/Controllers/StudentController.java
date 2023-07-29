package com.sampleProject.springboot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sampleProject.springboot.Entities.Student;
import com.sampleProject.springboot.Services.StudentService;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok(this.studentService.getStudent(id));
        } catch (IndexOutOfBoundsException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/students")
    public ResponseEntity<List<?>> getAllStudents() {
        try {
            return ResponseEntity.ok(this.studentService.getStudents());
        } catch (NullPointerException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
