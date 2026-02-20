package itis.dis403;

import itis.dis403.component.Application;
import itis.dis403.config.Context;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Запуск контекста ===");

        // Создаем контекст
        Context context = new Context();

        // Получаем Application из контекста
        Application app = (Application) context.getBean(Application.class);

        if (app != null) {
            System.out.println("✓ Application найден, запускаем...\n");
            app.run();
        } else {
            System.out.println("✗ Application не найден!");
        }

        // Выводим все маппинги
        context.printMappings();

        System.out.println("\n=== Готово ===");
    }
}