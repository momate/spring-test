package com.momate.springtest.model;

public class Employee {

    private Long id;
    private String name;
    private Long departmentId;
    private Long salary;

    public Employee() {

    }

    public Employee(Long id, String name, Long departmentId, Long salary) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
