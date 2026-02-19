package itis.dis403.component;

import itis.dis403.annotation.Component;
import itis.dis403.model.Basa;
import itis.dis403.model.Fruit;
import itis.dis403.model.FruitType;
import itis.dis403.model.Store;
import java.util.List;

@Component
public class StoreService {
    private Basa basa = new Basa();

    public void add(String name) {
        basa.getStores().add(new Store(name));
    }

    public void addFruit(Store store, Fruit fruit, Integer count) {
        store.getFruits().put(fruit, count);
    }

    public List<Store> getAll() {
        return basa.getStores();
    }

    public Store findByName(String name) {
        return basa.getStores().stream()
                .filter(s -> s.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public long getCountFruitByStore(String name, FruitType type) {
        return basa.getStores().stream()
                .filter(s -> s.getName().equals(name))
                .findFirst()
                .orElseThrow()
                .getFruits()
                .entrySet().stream().filter(e -> e.getKey().getType() == type)
                .count();
    }
}