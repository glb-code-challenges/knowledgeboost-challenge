package com.example.group.demoArtifact01;

import com.example.group.demoArtifact01.student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
/*@ComponentScan*/
/*@Configuration
@EnableAutoConfiguration
@ComponentScan*/
public class DemoArtifact01Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoArtifact01Application.class, args);
	}



}
