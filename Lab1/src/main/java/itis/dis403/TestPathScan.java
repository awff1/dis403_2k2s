package itis.dis403;

import itis.dis403.config.Context;
import itis.dis403.config.PathScan;

import java.util.List;

public class TestPathScan {
    public static void main(String[] args) {


        List<Class<?>> classes = PathScan.find("itis.dis403");
        classes.forEach(System.out::println);

        new Context();

    }
}