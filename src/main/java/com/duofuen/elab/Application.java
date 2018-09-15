package com.duofuen.elab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*(scanBasePackages = "com.duofuen.elab")*/
//@EnableJpaRepositories("com.duofuen.elab.domain.dao")
//@EntityScan("com.duofuen.elab.db.entity")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
