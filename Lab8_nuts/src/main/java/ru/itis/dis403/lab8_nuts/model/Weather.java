package ru.itis.dis403.lab8_nuts.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    private String city;
    private Double temp;
    private Double pressure;
    private Double windSpeed;
    private String windDirection;
    private LocalDateTime dateTime;

}
