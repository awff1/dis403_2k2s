package itis.dis403.controller;

import itis.dis403.annotation.Controller;
import itis.dis403.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class HomeController {

    @GetMapping("/home")
    public void home(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><head><title>Home</title></head><body>");
        writer.println("<h1>Домашняя страница</h1>");
        writer.println("<p>Метод: " + req.getMethod() + "</p>");
        writer.println("<p>Путь: " + req.getRequestURI() + "</p>");
        writer.println("<a href='/index'>Перейти на Index</a><br>");
        writer.println("<a href='/about'>О нас</a>");
        writer.println("</body></html>");
    }

    @GetMapping("/index")
    public void index(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><head><title>Index</title></head><body>");
        writer.println("<h1>Индексная страница</h1>");
        writer.println("<p>Это страница /index</p>");
        writer.println("<a href='/home'>На главную</a>");
        writer.println("</body></html>");
    }
}