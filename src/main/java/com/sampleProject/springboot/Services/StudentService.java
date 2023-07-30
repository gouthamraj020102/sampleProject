package com.sampleProject.springboot.Services;

import com.sampleProject.springboot.Exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sampleProject.springboot.Entities.Student;
import com.sampleProject.springboot.Repositories.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student getStudent(int id) {
        Student student = this.studentRepository.getListOfStudents().get(id-1);
        if(student == null)
            throw new IndexOutOfBoundsException();
        else
            return student;
    }

    public List<Student> getStudents() {
        List<Student> listOfStudents = this.studentRepository.getListOfStudents();
        if (listOfStudents.isEmpty())
            throw new NullPointerException();
        else
            return listOfStudents;
    }

    public void addStudent(Student student) {
        if (student.getId() > 0)
            this.studentRepository.addStudent(student);
        else
            throw new IllegalArgumentException("Invalid ID. The ID must be greater than 0.");
    }

    public void putStudent(Student student) throws StudentNotFoundException {
        List<Student> listOfStudents = this.studentRepository.getListOfStudents();
        for (Student updatedStudent : listOfStudents) {
            if (updatedStudent.getId() == student.getId()) {
                listOfStudents.set(listOfStudents.indexOf(updatedStudent), student);
                return;
            }
        }
        throw new StudentNotFoundException("Student with the specified ID not found for update.");
    }
}