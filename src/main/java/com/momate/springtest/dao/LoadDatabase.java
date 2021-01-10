package com.momate.springtest.dao;

import com.momate.springtest.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository,
                                   DepartmentRepository departmentRepository) {

        Department d1 = new Department("Big Department", "Budapest");
        Department d2 = new Department("Small Department", "Moscow");

        return args -> {
            log.info("Preloading " + departmentRepository.save(d1).toString());
            log.info("Preloading " + departmentRepository.save(d2).toString());


        };
    }
}
