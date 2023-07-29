package com.sampleProject.springboot.Repositories;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import org.springframework.stereotype.Repository;

import com.sampleProject.springboot.Entities.Student;

@Data
@Repository
public class StudentRepository {

    private List<Student> listOfStudents = new ArrayList<>();

}
