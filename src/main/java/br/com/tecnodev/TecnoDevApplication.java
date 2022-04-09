package br.com.tecnodev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class TecnoDevApplication {

    public static void main(String[] args) {
        SpringApplication.run(TecnoDevApplication.class, args);
    }

}
