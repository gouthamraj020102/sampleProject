package com.sampleProject.springboot.Services;

import com.sampleProject.springboot.Entities.Employee;
import com.sampleProject.springboot.Repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

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
}
