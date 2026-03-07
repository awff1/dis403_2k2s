package ru.itis.context.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.itis.context.components.MarketService;
import ru.itis.context.model.Market;

@Configuration
@ComponentScan("ru.itis.context.components")
public class Config {


    @Bean(name = "App")
    public MarketService getService() {
        Market market = new Market();
        return new MarketService(market);
    }
}