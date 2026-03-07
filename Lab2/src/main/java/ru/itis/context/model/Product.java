package ru.itis.context.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private String name;
    private String articul;
    private Category category;
    private BigDecimal price;

    public Product(String name, String articul, Category category, BigDecimal price) {
        this.name = name;
        this.articul = articul;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getArticul() {
        return articul;
    }

    public Category getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArticul(String articul) {
        this.articul = articul;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(articul, product.articul);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", articul='" + articul + '\'' +
                ", category=" + category +
                ", price=" + price +
                '}';
    }
}
