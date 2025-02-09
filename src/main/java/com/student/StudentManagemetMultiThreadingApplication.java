package com.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StudentManagemetMultiThreadingApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagemetMultiThreadingApplication.class, args);
	}

}
