package com.sampleProject.springboot.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.sampleProject.springboot.Entities.Student;
import com.sampleProject.springboot.Repositories.StudentRepository;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void getOneStudentTest() {
        Student student = new Student(1, "Gowtham", "Gow", "SDE1");
        when(studentRepository.getStudentById(10)).thenReturn(student);
        assertNotNull(studentService.getStudent(10));
    }

    @Test
    public void getStudentExceptionTest() {
        when(studentRepository.getStudentById(anyInt())).thenReturn(null);
        assertThrows(IndexOutOfBoundsException.class, () -> studentService.getStudent(0));
    }
}
