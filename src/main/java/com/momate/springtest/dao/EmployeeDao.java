package com.momate.springtest.dao;

import com.momate.springtest.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDao {

    private static List<Employee> DB = new ArrayList<>();

    public Employee save(Employee employee) {
        DB.add(employee);
        return employee;
    }

    public List<Employee> findAll() {
        return DB;
    }

    public Optional<Employee> findById(Long id) {
        for (Employee e : DB) {
            if (id.equals(e.getId())) {
                return Optional.of(e);
            }
        }
        return Optional.empty();
    }

}
