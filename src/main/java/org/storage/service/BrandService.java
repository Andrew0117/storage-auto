package org.storage.service;

import org.springframework.stereotype.Service;
import org.storage.jpa.BrandJpa;
import org.storage.vw.BrandVW;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {

    private BrandJpa brandJpa;

    public BrandService(BrandJpa brandJpa) {
        this.brandJpa = brandJpa;
    }

    public List<BrandVW> getAllBrand() {

        return brandJpa
                .findAll()
                .stream()
                .map(BrandVW::new)
                .collect(Collectors.toList());
    }
}
