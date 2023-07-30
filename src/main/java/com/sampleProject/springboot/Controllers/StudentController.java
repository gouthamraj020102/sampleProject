package com.sampleProject.springboot.Controllers;

import com.sampleProject.springboot.Exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            return ResponseEntity.ok(this.studentService.getStudents());
        } catch (NullPointerException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/student")
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        try {
            this.studentService.addStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body("Posted successfully");
        }
        catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID");
        }

    }

    @PutMapping("/update")
    public ResponseEntity<String> updateStudent(@RequestBody Student student) {
        try {
            this.studentService.putStudent(student);
            return ResponseEntity.ok("Successfully updated student");
        }
        catch (StudentNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
