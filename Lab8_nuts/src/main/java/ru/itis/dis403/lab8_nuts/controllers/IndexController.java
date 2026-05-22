package ru.itis.dis403.lab8_nuts.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.dis403.lab8_nuts.model.Weather;
import ru.itis.dis403.lab8_nuts.service.WeatherService;

import java.util.List;

@Controller
public class IndexController {
    WeatherService weatherService;

    public IndexController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String index(Model model) {

        Weather weather = weatherService.getWeather();
        model.addAttribute("weather", weather);


        return "index";
    }

}
