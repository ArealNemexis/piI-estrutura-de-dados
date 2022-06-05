package com.panelinha.transaction_scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TransactionSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionSchedulerApplication.class, args);
    }

}
