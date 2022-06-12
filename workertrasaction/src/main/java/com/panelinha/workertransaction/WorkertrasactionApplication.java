package com.panelinha.workertransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WorkertrasactionApplication {

    public static void main(String[] args) {
        System.out.println(System.getenv("DB_HOST"));
        System.out.println(System.getenv("RABBIT_HOST"));
        SpringApplication.run(WorkertrasactionApplication.class, args);
    }

}
