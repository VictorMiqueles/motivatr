package com.davmt.motivatr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableScheduling
public class MotivatrApplication {
  public static void main(String[] args) {
    SpringApplication.run(MotivatrApplication.class, args);
  }
}
