package com.lendlyspringboot.crud.springboot.demoproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.javaAbs.springboot.jdbc.model.Employee;
import com.javaAbs.springboot.jdbc.repository.EmployeeJDBCRepository;

@SpringBootApplication
@ComponentScan({"com.javaAbs.springboot.jdbc.repository"})
public class Application implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
 
    @Autowired
    private EmployeeJDBCRepository curse;
 
    @Override
    public void run(String... args) throws Exception {

        logger.info("Inserting -> {}", curse.insert(new Employee(151392, "John", "Lennon", "johnlennon@gmail.com")));
        logger.info("Inserting -> {}", curse.insert(new Employee(210981, "Paul", "McCartney", "PaulMc@gmail.com")));
        logger.info("Inserting -> {}", curse.insert(new Employee(128724, "Goerge", "Harrison", "HarrisonG@gmail.com")));
        logger.info("Inserting -> {}", curse.insert(new Employee(128724, "Ringo", "Starr", "RingoStarr@gmail.com")));
        
        
        
        logger.info("employee id 151392 -> {}", curse.findById(151392));

        logger.info("Update 128724 -> {}", curse.update(new Employee(200111, "Eric", "Clapton", "EClapton@gmail.com")));

        curse.deleteById(151392);

        logger.info("All users -> {}", curse.findAll());
    }
 
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

