package com.sampleProject.springboot.Repositories;

import com.sampleProject.springboot.Entities.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentRepositoryTest {

    @InjectMocks
    private StudentRepository studentRepository;

    @Test
    void addStudentTest() {
        Student student = new Student(1, "Gowtham", "Gow", "SDE1");
        this.studentRepository.addStudent(student);
        Assertions.assertTrue(studentRepository.getListOfStudents().contains(student));
    }
}
