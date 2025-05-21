package org.storage.restcontroller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.storage.service.ModelService;
import org.storage.vw.ModelVW;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api")
public class ModelRestController implements Serializable {

    private static final long serialVersionUID = 1129578841185515702L;

    private ModelService modelService;

    public ModelRestController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping(path = "/model/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ModelVW>> getModels(@PathVariable("id") UUID id) {

        return ResponseEntity.ok(this.modelService.getAllModel(id));
    }

    @PostMapping(path = "/model/comment", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> saveComment(@RequestBody ModelVW model) {

        this.modelService.saveComment(model);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/model/color/price", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> saveColorAndPrice(@RequestBody ModelVW model) {

        this.modelService.saveColorAndPrice(model);

        return ResponseEntity.noContent().build();
    }

}
