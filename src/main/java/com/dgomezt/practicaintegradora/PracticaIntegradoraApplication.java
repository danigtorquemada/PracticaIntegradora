package com.dgomezt.practicaintegradora;

import com.dgomezt.practicaintegradora.utilities.ConfProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class PracticaIntegradoraApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticaIntegradoraApplication.class, args);
    }

}
