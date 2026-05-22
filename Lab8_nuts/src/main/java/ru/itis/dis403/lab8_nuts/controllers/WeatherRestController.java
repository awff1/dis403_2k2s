package ru.itis.dis403.lab8_nuts.controllers;


import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dis403.lab8_nuts.model.Weather;
import ru.itis.dis403.lab8_nuts.service.WeatherService;

import javax.swing.text.html.parser.Entity;

@RestController("/api")
public class WeatherRestController {

    private final WeatherService weatherService;


    public WeatherRestController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/api/weather")
    public ResponseEntity<Weather> getWeather() {
        return ResponseEntity.ok(weatherService.getWeather());
    }
}
