package org.storage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.storage.filter.AutoFilter;
import org.storage.service.StorageAutoService;

@Controller
public class StorageAutoController {

    private StorageAutoService storageService;

    public StorageAutoController(StorageAutoService storageService) {
        this.storageService = storageService;
    }

    @GetMapping(value = "/storage-auto")
    public String storageAuto(Model model) {

        AutoFilter autoFilter = new AutoFilter();
        model.addAttribute("auto", autoFilter);
        model.addAttribute("storages", storageService.searchStorageAuto(autoFilter));

        return "storage-auto";
    }

    @PostMapping(value = "/storage-auto")
    public String createLogin(
            ModelMap model,
            @ModelAttribute(value = "auto") AutoFilter autoFilter
    ) {

        model.addAttribute("auto", autoFilter);
        model.addAttribute("storages", storageService.searchStorageAuto(autoFilter));

        return "storage-auto";
    }

}
