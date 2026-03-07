package ru.itis.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.context.components.Application;
import ru.itis.context.config.Config;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);


        context.getBean(Application.class).run();


    }
}
