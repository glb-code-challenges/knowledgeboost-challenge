package com.example.group.demoArtifact01.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository) {
        return args -> {
            Student mariam = new Student(
                    1L,
                    "mariam",
                    "mariam@q.com",
                    LocalDate.of(2000, Month.JANUARY, 5),
                    20


            );

            Student alex = new Student(
                    2L,
                    "alex",
                    "alex@q.com",
                    LocalDate.of(2000, Month.JANUARY, 5),
                    20


            );
            //repository.saveAll(List.(mariam, alex));
            repository.saveAll(Arrays.asList(mariam, alex));
        };

    }
}
