package org.storage.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.storage.jpa.ModelJpa;
import org.storage.vw.ModelVW;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ModelService {

    private ModelJpa modelJpa;

    public ModelService(ModelJpa modelJpa) {
        this.modelJpa = modelJpa;
    }

    public List<ModelVW> getAllModel(UUID idBrand) {

        return this.modelJpa
                .findModelsByBrandId(idBrand)
                .stream()
                .map(ModelVW::new)
                .collect(Collectors.toList());
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void saveComment(ModelVW modelVW) {

        this.modelJpa.saveComment(modelVW.getComment(), modelVW.getId());
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void saveColorAndPrice(ModelVW modelVW) {

        this.modelJpa.saveColorAndPrice(modelVW.getColor(), modelVW.getPrice(), modelVW.getId());
    }

}
