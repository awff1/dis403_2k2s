package itis.dis403.config;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PathScan {
    public static List<Class<?>> find(String scannedPackage) {
        List<Class<?>> classes = new ArrayList<>();
        String path = scannedPackage.replace('.', '/');
        URL url = Thread.currentThread().getContextClassLoader().getResource(path);

        if (url != null) {
            File directory = new File(url.getFile());
            scanDirectory(directory, scannedPackage, classes);
        }
        return classes;
    }

    private static void scanDirectory(File dir, String packageName, List<Class<?>> classes) {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                scanDirectory(file, packageName + "." + file.getName(), classes);
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + "." +
                        file.getName().substring(0, file.getName().length() - 6);
                try {
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException ignore) {}
            }
        }
    }
}