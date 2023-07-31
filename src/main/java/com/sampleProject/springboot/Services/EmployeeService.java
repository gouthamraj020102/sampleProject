package com.sampleProject.springboot.Services;

import com.sampleProject.springboot.Entities.Employee;
import com.sampleProject.springboot.Exception.EmployeeNotFoundException;
import com.sampleProject.springboot.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        if (employee != null) {
            return this.employeeRepository.save(employee);
        } else {
            throw new IllegalArgumentException("Employee details should not be null");
        }
    }

    public List<Employee> getEmployees() throws EmployeeNotFoundException {
        List<Employee> listOfEmployees = (List<Employee>) employeeRepository.findAll();
        if (!listOfEmployees.isEmpty())
            return listOfEmployees;
        else
            throw new EmployeeNotFoundException(null);
    }

    public Employee getEmployeeDetails(int id) throws EmployeeNotFoundException {
        Optional<Employee> employee = this.employeeRepository.findById(id);
        if (!employee.isEmpty())
            return employee.get();
        else
            throw new EmployeeNotFoundException(null);
    }
}
