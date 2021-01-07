package com.momate.springtest.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private Long departmentId;
    private Long salary;

    public Employee() {
    }

    public Employee(String firstName, String lastName, Long departmentId, Long salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.salary = salary;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public void setName(String name) {
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

}
