package ru.itis.dis403.lab8_nuts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itis.dis403.lab8_nuts.controllers.IndexController;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        System.out.println(context.getBean(IndexController.class));
    }

}
