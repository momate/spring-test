package com.momate.springtest.api;

import com.momate.springtest.model.Employee;
import com.momate.springtest.service.EmployeeService;
import org.hibernate.EntityMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private EmployeeModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<Employee>> getAllEmployee() {
        List<EntityModel<Employee>> employees = service.getAllEmployee().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(employees,
                linkTo(methodOn(EmployeeController.class).getAllEmployee()).withSelfRel());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addEmployee(@RequestBody Employee newEmployee) {
        EntityModel<Employee> entityModel =
                assembler.toModel(service.addEmployee(newEmployee));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

    }

    @GetMapping("/{id}")
    public EntityModel<Employee> getEmployeeById(@PathVariable Long id) {
        return assembler.toModel(
                service.getEmployeeById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id,
                                   @RequestBody Employee newEmployee) {
        EntityModel<Employee> entityModel =
                assembler.toModel(service.updateEmployee(id,newEmployee));
        
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        service.deleteEmployeeById(id);

        return ResponseEntity.noContent().build();
    }


}
