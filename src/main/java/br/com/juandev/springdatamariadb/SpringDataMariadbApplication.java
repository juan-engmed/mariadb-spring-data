package br.com.juandev.springdatamariadb;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringDataMariadbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataMariadbApplication.class, args);
    }

}
