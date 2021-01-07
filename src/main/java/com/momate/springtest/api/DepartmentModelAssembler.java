package com.momate.springtest.api;

import com.momate.springtest.model.Department;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DepartmentModelAssembler implements RepresentationModelAssembler<Department,
        EntityModel<Department>> {

    @Override
    public EntityModel<Department> toModel(Department entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(DepartmentController.class).getDepartmentById(entity.getId())).withSelfRel(),
                linkTo(methodOn(DepartmentController.class).getAllDepartment()).withRel("departments"));
    }
}
