package com.employee.attendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication(scanBasePackages = {"com.employee.attendance"})  
@EnableJpaRepositories(basePackages = {"com.employee.attendance.repository"})
@EntityScan("com.employee.attendance.entity")
@ComponentScan("com.employee.attendance.*")
@AutoConfiguration("com.employee.attendance.*")
@EnableKafka
public class AttendanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceApplication.class, args);
	}

}
