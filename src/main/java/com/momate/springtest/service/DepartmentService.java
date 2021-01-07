package com.momate.springtest.service;

import com.momate.springtest.dao.DepartmentRepository;
import com.momate.springtest.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    public List<Department> getAllDepartments() {
        return repository.findAll();
    }

    public Department addDepartment(Department d) {
        return repository.save(d);
    }

    public Department getDepartmentById(Long id) {
        return repository.findById(id).get();
    }

    public Department updateDepartment(Long id, Department newDepartment) {
        return repository.findById(id)
                .map(department -> {
                    department.setName(newDepartment.getName());
                    department.setAddress(newDepartment.getAddress());
                    return repository.save(department);
                })
                .orElseGet(() -> {
                    newDepartment.setId(id);
                    return repository.save(newDepartment);
                });
    }

    public void deleteDepartmentById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}