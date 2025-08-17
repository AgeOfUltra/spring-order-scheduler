package com.scheduler.springorderscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringOrderSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringOrderSchedulerApplication.class, args);
    }

}
