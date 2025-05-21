package org.storage.vw;

import org.storage.entity.Brand;

import java.util.UUID;

public class BrandVW {

    private UUID id;

    private String brand;

    public BrandVW() {
    }

    public BrandVW(final UUID id, final String brand) {
        this.id = id;
        this.brand = brand;
    }

    public BrandVW(Brand brand) {
        this.id = brand.getId();
        this.brand = brand.getBrand();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}

