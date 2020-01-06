package org.harvanir.pattern.clean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class CleanArchitectureApplication {

  public static void main(String[] args) {
    SpringApplication.run(CleanArchitectureApplication.class, args);
  }
}
