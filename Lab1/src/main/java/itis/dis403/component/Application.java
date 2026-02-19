package itis.dis403.component;

import itis.dis403.annotation.Component;
import itis.dis403.model.Fruit;
import itis.dis403.model.FruitType;
import itis.dis403.model.Store;

@Component
public class Application {

    //@Inject
    private StoreService service;

    public Application(StoreService service) {
        this.service = service;
    }

    public void run() {

        service.add("I");
        service.add("II");

        Store storeI = service.findByName("I");
        service.addFruit(storeI, new Fruit("Яблоко", FruitType.APPLE), 1000);
        service.addFruit(storeI, new Fruit("Банан", FruitType.BANANA), 2000);

        Store storeII = service.findByName("II");
        service.addFruit(storeI, new Fruit("Яблоко", FruitType.APPLE), 3000);
        service.addFruit(storeI, new Fruit("Апельсин", FruitType.ORANGE), 2000);

        service.getAll().forEach(System.out::println);
    }

}