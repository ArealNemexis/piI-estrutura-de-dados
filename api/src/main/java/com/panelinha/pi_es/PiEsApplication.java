package com.panelinha.pi_es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class PiEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiEsApplication.class, args);
    }

}
