package com.sampleProject.springboot.Repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sampleProject.springboot.Entities.Student;

@Repository
public class StudentRepository {

    public Student getStudentById(int id) {
        Student student = listOfStudents().get(id - 1);
        System.out.println(student);
        if (student != null)
            return student;
        else
            return null;
    }

    public List<Student> listOfStudents() {
        List<Student> listOfStudents = new ArrayList<>();
        listOfStudents.add(new Student(1, "Gowtham Raju", "Goutham", "SDE1"));
        listOfStudents.add(new Student(2, "Murali Vegiraju", "Murali", "SDE1"));
        listOfStudents.add(new Student(3, "Deepak", "Deepu", "SDE1"));
        listOfStudents.add(new Student(4, "VaraPrasad", "Prasad", "SDE2"));
        return listOfStudents;
    }
}
