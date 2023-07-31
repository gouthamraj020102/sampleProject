package com.sampleProject.springboot.Controllers;

import com.sampleProject.springboot.Entities.Employee;
import com.sampleProject.springboot.Exception.EmployeeNotFoundException;
import com.sampleProject.springboot.Services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    void createEmployee_happyPath() {
        when(employeeService.createEmployee(any(Employee.class))).thenReturn(list().get(0));
        assertEquals(HttpStatus.CREATED, employeeController.createEmployee(list().get(0)).getStatusCode());
        assertEquals(list().get(0), employeeController.createEmployee(list().get(0)).getBody());
    }

    @Test
    void createEmployee_ExceptionTest() {
        doThrow(IllegalArgumentException.class).when(employeeService).createEmployee(any(Employee.class));
        assertEquals(HttpStatus.BAD_REQUEST, employeeController.createEmployee(new Employee()).getStatusCode());
        assertNull(employeeController.createEmployee(new Employee()).getBody());
    }

    private List<Employee> list() {
        List<Employee> listOfEmployees = new ArrayList<>();
        listOfEmployees.add(new Employee(1, "Test", 1, "Bot"));
        return listOfEmployees;
    }

    @Test
    void getAllEmployees_happyPath() throws EmployeeNotFoundException {
        when(employeeService.getEmployees()).thenReturn(list());
        assertEquals(list(), employeeController.getAllEmployees().getBody());
        assertEquals(HttpStatus.OK, employeeController.getAllEmployees().getStatusCode());
    }

    @Test
    void getAllEmployees_ExceptionTest() throws EmployeeNotFoundException {
        doThrow(EmployeeNotFoundException.class).when(employeeService).getEmployees();
        assertEquals(HttpStatus.NOT_FOUND, employeeController.getAllEmployees().getStatusCode());
    }

    @Test
    void getEmployee_happyPath() throws EmployeeNotFoundException {
        when(employeeService.getEmployeeDetails(anyInt())).thenReturn(list().get(0));
        assertEquals(list().get(0), employeeController.getEmployee(1).getBody());
        assertEquals(HttpStatus.OK, employeeController.getEmployee(1).getStatusCode());
    }

    @Test
    void getEmployee_ExceptionTest() throws EmployeeNotFoundException {
        doThrow(EmployeeNotFoundException.class).when(employeeService).getEmployeeDetails(anyInt());
        assertEquals(HttpStatus.NOT_FOUND, employeeController.getEmployee(1).getStatusCode());
    }
}
