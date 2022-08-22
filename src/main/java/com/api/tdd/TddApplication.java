package com.api.tdd;

import com.api.tdd.repository.BookingRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={
        "com.api.tdd", "com.api.tdd.controller", "com.api.tdd.repository", "com.api.tdd.service", "com.api.tdd.model"})
@EnableJpaRepositories(basePackageClasses = BookingRepository.class)
public class TddApplication {

    public static void main(String[] args) {
        SpringApplication.run(TddApplication.class, args);
    }

}
