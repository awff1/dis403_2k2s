package itis.dis403.config;

import itis.dis403.annotation.Component;
import itis.dis403.annotation.Controller;
import itis.dis403.annotation.GetMapping;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Context {

    private String scanPath = "itis.dis403";

    // Хранилище для всех бинов (и компоненты, и контроллеры)
    private Map<Class<?>, Object> beans = new HashMap<>();

    // Маппинг: путь -> метод
    private Map<String, Method> mapping = new HashMap<>();

    // Маппинг: метод -> экземпляр контроллера
    private Map<Method, Object> controllerInstances = new HashMap<>();

    public Context() {
        // 1. Находим все классы в пакете
        List<Class<?>> classes = PathScan.find(scanPath);

        // 2. Сначала создаем все компоненты без зависимостей
        createSimpleBeans(classes);

        // 3. Потом создаем остальные (с зависимостями)
        createDependentBeans(classes);

        // 4. Собираем маппинги из контроллеров
        collectMappings();
    }

    // Создаем бины без параметров в конструкторе
    private void createSimpleBeans(List<Class<?>> classes) {
        for (Class<?> clazz : classes) {
            if (!hasAnnotation(clazz)) continue;

            try {
                // Пробуем создать через конструктор без параметров
                Object instance = clazz.getDeclaredConstructor().newInstance();
                beans.put(clazz, instance);
                System.out.println("Создан: " + clazz.getSimpleName());
            } catch (Exception e) {
                // Если нет конструктора без параметров, пропускаем - создадим позже
            }
        }
    }

    // Создаем бины с зависимостями
    private void createDependentBeans(List<Class<?>> classes) {
        for (Class<?> clazz : classes) {
            if (beans.containsKey(clazz)) continue;
            if (!hasAnnotation(clazz)) continue;

            try {
                // Берем первый конструктор
                Constructor<?> constructor = clazz.getConstructors()[0];
                Class<?>[] paramTypes = constructor.getParameterTypes();

                // Собираем аргументы из уже созданных бинов
                Object[] args = new Object[paramTypes.length];
                boolean allFound = true;

                for (int i = 0; i < paramTypes.length; i++) {
                    args[i] = beans.get(paramTypes[i]);
                    if (args[i] == null) {
                        allFound = false;
                        break;
                    }
                }

                if (allFound) {
                    Object instance = constructor.newInstance(args);
                    beans.put(clazz, instance);
                    System.out.println("Создан с зависимостями: " + clazz.getSimpleName());
                }
            } catch (Exception e) {
                System.out.println("Не удалось создать " + clazz.getSimpleName() + ": " + e.getMessage());
            }
        }
    }

    // Проверяем, есть ли нужная аннотация
    private boolean hasAnnotation(Class<?> clazz) {
        return clazz.isAnnotationPresent(Component.class) ||
                clazz.isAnnotationPresent(Controller.class);
    }

    // Собираем все @GetMapping из контроллеров
    private void collectMappings() {
        for (Object bean : beans.values()) {
            Class<?> clazz = bean.getClass();

            // Интересуют только контроллеры
            if (!clazz.isAnnotationPresent(Controller.class)) continue;

            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(GetMapping.class)) {
                    GetMapping annotation = method.getAnnotation(GetMapping.class);
                    String path = annotation.value();

                    mapping.put(path, method);
                    controllerInstances.put(method, bean);

                    System.out.println("Маппинг: " + path + " -> " +
                            clazz.getSimpleName() + "." + method.getName());
                }
            }
        }
    }

    // Получить бин по классу
    public Object getBean(Class<?> clazz) {
        return beans.get(clazz);
    }

    // Получить метод для пути
    public Method getMethod(String path) {
        return mapping.get(path);
    }

    // Получить экземпляр контроллера для метода
    public Object getControllerForMethod(Method method) {
        return controllerInstances.get(method);
    }

    // Для отладки
    public void printMappings() {
        System.out.println("\n=== МАППИНГИ ===");
        mapping.forEach((path, method) ->
                System.out.println(path + " -> " + method.getName()));
    }
}