package itis.dis403.controller;

import itis.dis403.annotation.Controller;
import itis.dis403.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class AboutController {

    @GetMapping("/about")
    public void about(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><head><title>About</title></head><body>");
        writer.println("<h1>О нас</h1>");
        writer.println("<p>Это простое веб-приложение со встроенным Tomcat</p>");
        writer.println("<p>Используются аннотации @Controller и @GetMapping</p>");
        writer.println("<a href='/home'>На главную</a>");
        writer.println("</body></html>");
    }
}