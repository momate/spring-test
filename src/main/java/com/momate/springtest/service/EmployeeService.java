package com.momate.springtest.service;

import com.momate.springtest.dao.EmployeeDao;
import com.momate.springtest.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao dao;

    public List<Employee> getAllEmployee() {
        return dao.findAll();
    }

    public Employee addEmployee(Employee e) {
        return dao.save(e);
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return dao.findById(id);
    }

}
