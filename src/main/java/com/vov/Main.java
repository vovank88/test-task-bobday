package com.vov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories
@EnableTransactionManagement
@SpringBootApplication
@PropertySource("file:config/application.yml")
public class Main {

  static {
    System.setProperty("spring.config.location", "config/");
  }

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

}
