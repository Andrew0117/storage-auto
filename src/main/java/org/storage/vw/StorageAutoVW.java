package org.storage.vw;

import org.storage.entity.Brand;
import org.storage.entity.Model;

import java.math.BigDecimal;
import java.util.UUID;

public class StorageAutoVW {

    private UUID idBrand;

    private UUID idModel;

    private String brand;

    private String model;

    private String comment;

    private String color;

    private BigDecimal price;

    public StorageAutoVW() {
    }

    public StorageAutoVW(UUID idBrand, String brand, UUID idModel, String model, String comment, String color, BigDecimal price) {
        this.idBrand = idBrand;
        this.brand = brand;
        this.idModel = idModel;
        this.model = model;
        this.comment = comment;
        this.color = color;
        this.price = price;
    }

    public StorageAutoVW(Brand brand, Model model) {
        this.idBrand = brand.getId();
        this.brand = brand.getBrand();
        this.idModel = model.getId();
        this.model = model.getModel();
        this.comment = model.getComment();
        this.color = model.getColor();
        this.price = model.getPrice();
    }

    public UUID getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(UUID idBrand) {
        this.idBrand = idBrand;
    }

    public UUID getIdModel() {
        return idModel;
    }

    public void setIdModel(UUID idModel) {
        this.idModel = idModel;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Model toModel() {
        Model model = new Model();
        model.setId(this.idModel);
        model.setModel(this.model);
        model.setComment(this.comment);
        model.setColor(this.color);
        model.setPrice(this.price);

        return model;
    }

    public Brand toBrand() {
        Brand brand = new Brand();
        brand.setId(this.idBrand);
        brand.setBrand(this.brand);

        return brand;
    }
}
