package ru.itis.orm.model;

import ru.itis.orm.annotation.Column;
import ru.itis.orm.annotation.Entity;
import ru.itis.orm.annotation.Id;
import ru.itis.orm.annotation.ManyToOne;

@Entity
public class Street {

    @Id
    private Long id;

    @Column
    private String name;

    @ManyToOne
    private City city;


    public Street() {
    }

    public Street(Long id, String name, City city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Street{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                '}';
    }
}
