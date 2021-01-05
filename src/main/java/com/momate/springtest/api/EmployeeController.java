package com.momate.springtest.api;

import com.momate.springtest.model.Employee;
import com.momate.springtest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping
    public CollectionModel<EntityModel<Employee>> getAllEmployee() {
        List<EntityModel<Employee>> employeeList = service.getAllEmployee().stream()
                .map(employee -> EntityModel.of(employee,

                        linkTo(methodOn(EmployeeController.class).getEmployeeById(employee.getId())).withSelfRel(),

                        linkTo(methodOn(EmployeeController.class).getAllEmployee()).withRel("employees")))
                .collect(Collectors.toList());

        return CollectionModel.of(employeeList,
                linkTo(methodOn(EmployeeController.class).getAllEmployee()).withSelfRel());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee newEmployee) {
        return service.addEmployee(newEmployee);

    }

    @GetMapping("/{id}")
    public EntityModel<Employee> getEmployeeById(@PathVariable Long id) {
        return EntityModel.of(service.getEmployeeById(id),
                linkTo(methodOn(EmployeeController.class).getEmployeeById(id)).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).getAllEmployee()).withRel("employees")
        );
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id,
                                                @RequestBody Employee newEmployee) {
        return service.updateEmployee(id, newEmployee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteEmployee(@PathVariable Long id) {
        service.deleteEmployeeById(id);
    }


}
