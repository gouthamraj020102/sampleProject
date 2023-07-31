package com.sampleProject.springboot.Services;

import com.sampleProject.springboot.Entities.Employee;
import com.sampleProject.springboot.Exception.EmployeeNotFoundException;
import com.sampleProject.springboot.Repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void createEmployee_happyPath() {
        when(employeeRepository.save(any())).thenReturn(listofEmployees().get(0));
        Employee employee = employeeService.createEmployee(new Employee());
        assertNotNull(employee);
        assertEquals(listofEmployees().get(0), employee);
    }

    @Test
    void createEmployee_ExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> employeeService.createEmployee(null));
    }

    private List<Employee> listofEmployees() {
        List<Employee> listOfEmployees = new ArrayList<>();
        listOfEmployees.add(new Employee(1, "Test", 1, "Bot"));
        return listOfEmployees;
    }

    @Test
    void getEmployees_happyPath() throws EmployeeNotFoundException {
        when(employeeRepository.findAll()).thenReturn(listofEmployees());
        assertEquals(listofEmployees(), employeeService.getEmployees());
    }

    @Test
    void getEmployees_ExceptionTest() {
        when(employeeRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployees());
    }

    @Test
    void getEmployeeDetails_happyPath() throws EmployeeNotFoundException {
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.ofNullable(listofEmployees().get(0)));
        assertEquals(listofEmployees().get(0), employeeService.getEmployeeDetails(0));
    }

    @Test
    void getEmployeeDetails_ExceptionTest() {
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeDetails(0));
    }
}
