package ru.itis.dis403.lab8_nuts.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.Connection;
import io.nats.client.Message;
import io.nats.client.Nats;
import io.nats.client.Subscription;
import org.springframework.stereotype.Service;
import ru.itis.dis403.lab8_nuts.model.Weather;

import java.io.IOException;
import java.time.Duration;

@Service
public class WeatherService {


    // Подключаемся к брокеру, оформляем подписку, запрашиваем сообщение, ждем 3 секунды и дальше разрываем
    public Weather getWeather() {
        String subject = "11-403.messages";


        Weather result = null;
        try (Connection nc = Nats.connect("nats://147.45.199.55:4222")) {


            Subscription sub = nc.subscribe(subject);
            Message msg = sub.nextMessage(Duration.ofSeconds(3));

            if (msg != null) {
                ObjectMapper mapper = new ObjectMapper();
                result = mapper.readValue(msg.getData(), Weather.class);
                System.out.println("Получено сообщение: " + result);
            } else {
                System.out.println("Сообщение не получено.");
            }
        return result;

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
