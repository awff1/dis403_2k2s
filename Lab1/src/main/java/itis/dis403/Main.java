package itis.dis403;

import itis.dis403.component.Application;
import itis.dis403.config.Context;

public class Main {
    public static void main(String[] args) {
        Context context = new Context();

        Application application = (Application) context.getComponent(Application.class);
        application.run();
    }
}