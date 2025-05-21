package org.storage.restcontroller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.storage.service.BrandService;
import org.storage.vw.BrandVW;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class BrandRestController implements Serializable {

    private static final long serialVersionUID = -7680599843631958705L;

    private BrandService brandService;

    public BrandRestController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping(path = "/brand", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BrandVW>> getBrands() {

        return ResponseEntity.ok(this.brandService.getAllBrand());
    }

}
