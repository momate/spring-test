package com.momate.springtest.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    private String name;
    private String address;


    public Department() {
    }

    public Department(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
