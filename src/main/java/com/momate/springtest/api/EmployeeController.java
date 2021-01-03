package com.momate.springtest.api;

import com.momate.springtest.model.Employee;
import com.momate.springtest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping
    public List<Employee> getAllEmployee(){
        return service.getAllEmployee();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee newEmployee) {
        return service.addEmployee(newEmployee);

    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
       return service.getEmployeeById(id).get();
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id,
                                   @RequestBody Employee newEmployee){
        return service.updateEmployee(id,newEmployee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteEmployee(@PathVariable Long id){
        service.deleteEmployeeById(id);
    }


}
