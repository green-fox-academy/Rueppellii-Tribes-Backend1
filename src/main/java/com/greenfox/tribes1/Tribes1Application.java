package com.greenfox.tribes1;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tribes1Application {
  
  public static void main(String[] args) {
    
    SpringApplication.run(Tribes1Application.class, args);
    System.out.println("Hello World!");
    Flyway flyway = Flyway.configure().dataSource(System.getenv("database"), System.getenv("db_username"), System.getenv("db_password")).load();
    flyway.clean();
    flyway.baseline();
    //System.getenv("ABC") --> get the env. variable called "ABC"
    flyway.migrate();
    
  }
  
}