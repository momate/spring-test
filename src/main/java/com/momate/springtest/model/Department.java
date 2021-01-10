package com.momate.springtest.model;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    @OneToMany(mappedBy = "department" , cascade = CascadeType.ALL)
    private List<Employee> employees;

    public Department() {
    }

    public Department(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
