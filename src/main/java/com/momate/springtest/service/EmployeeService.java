package com.momate.springtest.service;

import com.momate.springtest.dao.EmployeeRepository;
import com.momate.springtest.exception.EmployeeNotFoundException;
import com.momate.springtest.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployee() {
        return repository.findAll();
    }

    public Employee addEmployee(Employee e) {
        return repository.save(e);
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Employee updateEmployee(Long id, Employee newEmployee) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setDepartmentId(newEmployee.getDepartmentId());
                    employee.setSalary(newEmployee.getSalary());
                    return repository.save(employee);
                })
                .orElseGet(()->{
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    public void deleteEmployeeById(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
