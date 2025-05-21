package org.storage.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "model", catalog = "storage")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "model")
    private String model;

    @Column(name = "comment")
    private String comment;

    @Column(name = "color")
    private String color;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fkBrand")
    private Brand brand;

    public Model() {
    }

    public Model(UUID id, String comment, String color, BigDecimal price) {
        this.id = id;
        this.comment = comment;
        this.color = color;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Model model1 = (Model) o;
        return Objects.equals(id, model1.id) && Objects.equals(model, model1.model) && Objects.equals(comment, model1.comment) && Objects.equals(color, model1.color) && Objects.equals(price, model1.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, comment, color, price);
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", comment='" + comment + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}
