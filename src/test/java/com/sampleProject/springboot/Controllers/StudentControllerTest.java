package com.sampleProject.springboot.Controllers;

import com.sampleProject.springboot.Entities.Student;
import com.sampleProject.springboot.Services.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void getStudent_happyPath() {
        Student student = new Student(1, "Gowtham", "Gow", "SDE1");
        when(studentService.getStudent(anyInt())).thenReturn(student);
        assertNotNull(studentController.getStudent(10));
        assertEquals(HttpStatus.OK, studentController.getStudent(10).getStatusCode());
    }

    @Test
    public void getStudent_nullResponse() {
        doThrow(IndexOutOfBoundsException.class).when(studentService).getStudent(anyInt());
        assertEquals(HttpStatus.NOT_FOUND, studentController.getStudent(10).getStatusCode());
        assertNull(studentController.getStudent(10).getBody());
    }

    @Test
    public void getAllStudents_happyPath() {
        List<Student> listOfStudents = new ArrayList<>();
        listOfStudents.add(new Student(1, "Gowtham", "Gow", "SDE1"));
        when(studentService.getStudents()).thenReturn(listOfStudents);
        assertNotNull(studentController.getAllStudents().getBody());
        assertEquals(listOfStudents, studentController.getAllStudents().getBody());
        assertEquals(HttpStatus.OK, studentController.getAllStudents().getStatusCode());
    }

    @Test
    public void getAllStudents_nullResponse() {
        doThrow(NullPointerException.class).when(studentService).getStudents();
        assertEquals(HttpStatus.NOT_FOUND, studentController.getAllStudents().getStatusCode());
        assertNull(studentController.getAllStudents().getBody());
    }
}
