package com.pythagorasweb.amigoscode.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.util.Calendar.JULY;
import static java.util.Calendar.MAY;

@Configuration
public class StudentConfig {

    //Create a bean
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student pythagoras = new Student(
                    "Pythagoras","pythagorasweb58@gmail.com",
                    LocalDate.of(1996, JULY,12)
            );

            Student solomon = new Student(
                    "solomon","solomon@gmail.com",
                    LocalDate.of(1996, MAY,12)
            );

            //save the students into db
            repository.saveAll(
                    List.of(pythagoras,solomon)
            );
        };
    }
}
