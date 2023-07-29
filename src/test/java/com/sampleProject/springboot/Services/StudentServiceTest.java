package com.sampleProject.springboot.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.sampleProject.springboot.Entities.Student;
import com.sampleProject.springboot.Repositories.StudentRepository;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void getOneStudentTest() {
        when(studentRepository.getListOfStudents()).thenReturn(listOfStudents());
        assertNotNull(studentService.getStudent(1));
        assertEquals(listOfStudents().get(0), studentService.getStudent(1));
    }

    @Test
    void getStudentExceptionTest() {
        when(studentRepository.getListOfStudents()).thenReturn(new ArrayList<>());
        assertThrows(IndexOutOfBoundsException.class, () -> studentService.getStudent(10));
    }

    private List<Student> listOfStudents() {
        List<Student> listOfStudents = new ArrayList<>();
        listOfStudents.add(new Student(1, "Gowtham", "Gow", "SDE1"));
        return listOfStudents;
    }

    @Test
    void getStudents_happyPath() {
        when(studentRepository.getListOfStudents()).thenReturn(listOfStudents());
        assertNotNull(studentService.getStudents());
    }

    @Test
    void getStudentsExceptionTest() {
        when(studentRepository.getListOfStudents()).thenReturn(new ArrayList<>());
        assertThrows(NullPointerException.class, () -> studentService.getStudents());
    }

    private Student student = new Student(1, "Gowtham", "Gow", "SDE1");

    @Test
    void addStudent_happyPath() {
        doNothing().when(studentRepository).addStudent(any());
        studentService.addStudent(student);
        verify(studentRepository, times(1)).addStudent(student);
    }

    @Test
    void addStudentExceptionTest() {
        Student student = new Student();
        assertThrows(IllegalArgumentException.class, () -> studentService.addStudent(student));
    }
}
