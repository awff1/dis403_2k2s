package itis.dis403.servlet;

import itis.dis403.config.Context;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class DispatcherServlet extends HttpServlet {

    private Context context;

    @Override
    public void init() throws ServletException {
        super.init();
        context = new Context();
        context.printMappings();
        System.out.println("DispatcherServlet готов к работе!");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String path = req.getRequestURI();
        System.out.println("Запрос: " + path);

        // Ищем метод
        Method method = context.getMethod(path);

        if (method != null) {
            try {
                Object controller = context.getControllerForMethod(method);
                method.invoke(controller, req, resp);
            } catch (Exception e) {
                e.printStackTrace();
                sendError(resp, 500, "Ошибка: " + e.getMessage());
            }
        } else {
            sendError(resp, 404, "Страница не найдена: " + path);
        }
    }

    private void sendError(HttpServletResponse resp, int code, String message)
            throws IOException {
        resp.setStatus(code);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><body>");
        writer.println("<h1>Ошибка " + code + "</h1>");
        writer.println("<p>" + message + "</p>");
        writer.println("<a href='/home'>На главную</a>");
        writer.println("</body></html>");
    }
}