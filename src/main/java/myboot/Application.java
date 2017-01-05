package myboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
@Configuration
@RestController
@EnableAutoConfiguration
@ComponentScan
public class Application implements EmbeddedServletContainerCustomizer{

  @RequestMapping("/")
  public String home() {
    return "Hello,MyBoot";
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
  @Override  
  public void customize(ConfigurableEmbeddedServletContainer container) {  
      container.setPort(8099);  
  }  
}