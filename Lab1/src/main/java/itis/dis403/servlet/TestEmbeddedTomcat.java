package itis.dis403.servlet;

import jakarta.servlet.http.HttpServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class TestEmbeddedTomcat {
    public static void main(String[] args) {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("temp");

        Connector conn = new Connector();
        conn.setPort(8090);
        tomcat.setConnector(conn);

        String contextPath = "";

        // Используем временную директорию для docBase
        String docBase = new File(System.getProperty("java.io.tmpdir")).getAbsolutePath();
        Context tomcatContext = tomcat.addContext(contextPath, docBase);

        // Создаем экземпляр DispatcherServlet
        HttpServlet dispatcherServlet = new DispatcherServlet();

        // Добавляем сервлет
        String servletName = "dispatcherServlet";
        tomcat.addServlet(contextPath, servletName, dispatcherServlet);

        // Маппинг на все запросы
        tomcatContext.addServletMappingDecoded("/*", servletName);

        try {
            tomcat.start();
            System.out.println("==========================================");
            System.out.println("Сервер запущен на http://localhost:8090/");
            System.out.println("Доступные страницы:");
            System.out.println("  - http://localhost:8090/home");
            System.out.println("  - http://localhost:8090/index");
            System.out.println("  - http://localhost:8090/about");
            System.out.println("==========================================");
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
    }
}