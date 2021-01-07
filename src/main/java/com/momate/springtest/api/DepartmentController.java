package com.momate.springtest.api;

import com.momate.springtest.model.Department;
import com.momate.springtest.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequestMapping("departments")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @Autowired
    private DepartmentModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<Department>> getAllDepartment() {
        List<EntityModel<Department>> modelList =
                service.getAllDepartments().stream()
                        .map(assembler::toModel)
                        .collect(Collectors.toList());

        return CollectionModel.of(modelList,
                linkTo(methodOn(DepartmentController.class)
                        .getAllDepartment()).withSelfRel());

    }

    @GetMapping("/{id}")
    public EntityModel<Department> getDepartmentById(@PathVariable Long id) {
        return assembler.toModel(
                service.getDepartmentById(id));
    }

    @PostMapping
    public ResponseEntity<?> addDepartment(@RequestBody Department newDepartment) {
        EntityModel<Department> entityModel =
                assembler.toModel(service.addDepartment(newDepartment));

        return ResponseEntity.created(
                entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable Long id,
                                              @RequestBody Department updatedDepartment) {
        EntityModel<Department> entityModel =
                assembler.toModel(service.updateDepartment(id, updatedDepartment));
        return ResponseEntity.created(
                entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartmentById(@PathVariable Long id) {
        service.deleteDepartmentById(id);
        return ResponseEntity.noContent().build();
    }


}
