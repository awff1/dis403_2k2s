package ru.itis.context.components;

import org.springframework.stereotype.Component;
import ru.itis.context.model.Category;
import ru.itis.context.model.Market;
import ru.itis.context.model.Order;
import ru.itis.context.model.Product;

import java.math.BigDecimal;

@Component
public class Application {


    private final MarketService service;


    public Application(MarketService service) {
        this.service = service;
    }

    public void run() {
        try {
            service.doOrder(new Order(
                    new Product("Comp", "2312653", Category.PC, BigDecimal.valueOf(50000)),
                    10,
                    "client1"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
