package com.sampleProject.springboot.Controllers;

import com.sampleProject.springboot.Entities.Student;
import com.sampleProject.springboot.Exception.StudentNotFoundException;
import com.sampleProject.springboot.Services.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    void getStudent_happyPath() {
        Student student = new Student(1, "Gowtham", "Gow", "SDE1");
        when(studentService.getStudent(anyInt())).thenReturn(student);
        assertNotNull(studentController.getStudent(10));
        assertEquals(HttpStatus.OK, studentController.getStudent(10).getStatusCode());
    }

    @Test
    void getStudent_nullResponse() {
        doThrow(IndexOutOfBoundsException.class).when(studentService).getStudent(anyInt());
        assertEquals(HttpStatus.NOT_FOUND, studentController.getStudent(10).getStatusCode());
        assertNull(studentController.getStudent(10).getBody());
    }

    @Test
    void getAllStudents_happyPath() {
        List<Student> listOfStudents = new ArrayList<>();
        listOfStudents.add(new Student(1, "Gowtham", "Gow", "SDE1"));
        when(studentService.getStudents()).thenReturn(listOfStudents);
        assertNotNull(studentController.getAllStudents().getBody());
        assertEquals(listOfStudents, studentController.getAllStudents().getBody());
        assertEquals(HttpStatus.OK, studentController.getAllStudents().getStatusCode());
    }

    @Test
    void getAllStudents_nullResponse() {
        doThrow(NullPointerException.class).when(studentService).getStudents();
        assertEquals(HttpStatus.NOT_FOUND, studentController.getAllStudents().getStatusCode());
        assertNull(studentController.getAllStudents().getBody());
    }

    private Student student = new Student(1, "Gowtham", "Gow", "SDE1");

    @Test
    void createStudent_happyPath() {
        doNothing().when(studentService).addStudent(any(Student.class));
        assertNotNull(studentController.createStudent(student).getBody());
        assertEquals(HttpStatus.CREATED, studentController.createStudent(student).getStatusCode());
    }

    @Test
    void createStudent_ExceptionTest() {
        doThrow(IllegalArgumentException.class).when(studentService).addStudent(any(Student.class));
        assertNotNull(studentController.createStudent(student).getBody());
        assertEquals(HttpStatus.BAD_REQUEST, studentController.createStudent(student).getStatusCode());
    }

    @Test
    void updateStudent_happyPath() throws StudentNotFoundException {
        doNothing().when(studentService).putStudent(any());
        assertNotNull(studentController.updateStudent(student).getBody());
        assertEquals(HttpStatus.OK, studentController.updateStudent(student).getStatusCode());
    }

    @Test
    void updateStudent_ExceptionTest() throws StudentNotFoundException {
        doThrow(new StudentNotFoundException("Student not found")).when(studentService).putStudent(any());
        assertNotNull(studentController.updateStudent(student).getBody());
        assertEquals(HttpStatus.NOT_FOUND, studentController.updateStudent(student).getStatusCode());
    }
}
