package org.storage.vw;

import org.storage.entity.Model;

import java.math.BigDecimal;
import java.util.UUID;

public class ModelVW {

    private UUID id;

    private String model;

    private String comment;

    private String color;

    private BigDecimal price;

    public ModelVW() {
    }

    public ModelVW(UUID id, String model, String comment, String color, BigDecimal price) {
        this.id = id;
        this.model = model;
        this.comment = comment;
        this.color = color;
        this.price = price;
    }

    public ModelVW(Model model) {
        this.id = model.getId();
        this.model = model.getModel();
        this.comment = model.getComment();
        this.color = model.getColor();
        this.price = model.getPrice();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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
}
