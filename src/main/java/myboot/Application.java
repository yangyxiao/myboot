package myboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
@Configuration
@RestController
@EnableAutoConfiguration
public class Application {

  @RequestMapping("/")
  public String home() {
    return "Hello";
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}