package org.storage.restcontroller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.storage.service.StorageAutoService;
import org.storage.vw.StorageAutoVW;

import java.io.Serializable;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api")
public class StorageAutoRestController implements Serializable {

    private static final long serialVersionUID = 1969102596603678470L;

    private StorageAutoService storageAutoService;

    public StorageAutoRestController(StorageAutoService storageAutoService) {
        this.storageAutoService = storageAutoService;
    }

    @PostMapping(path = "/storage", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StorageAutoVW> isStorageAuto(@RequestBody StorageAutoVW storageAutoVW) {

        StorageAutoVW storageAuto = this.storageAutoService.saveStorageAuto(storageAutoVW);

        return ResponseEntity.ok(storageAuto);
    }

    @DeleteMapping(path = "/storage/{idBrand}/{idModel}/")
    public ResponseEntity<Void> deleteStorageAuto(@PathVariable UUID idBrand, @PathVariable UUID idModel) {

        this.storageAutoService.deleteStorageAuto(idBrand, idModel);

        return ResponseEntity.noContent().build();
    }

}
