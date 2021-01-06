package com.momate.springtest.dao;

import com.momate.springtest.model.Department;
import com.momate.springtest.model.Employee;
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
                                   DepartmentRepository departmentRepository){
        Employee e1 = new Employee("Tom", "Tom", null,1000L);
        Employee e2 = new Employee("Don", "Don", null,2000L);

        return args -> {
            log.info("Preloading " + employeeRepository.save(e1));
            log.info("Preloading " + employeeRepository.save(e2));

            log.info("Preloading " + departmentRepository.save(new Department("Big Department", "Budapest")));
            log.info("Preloading " + departmentRepository.save(new Department("Small Department", "Moscow")));
        };
    }
}
