package org.example.controller;

import org.example.model.dto.PlantDTO;
import org.example.service.interfeces.PlantService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plant")
public class PlantController {

    private final PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @PostMapping
    public PlantDTO savePlant(@RequestBody PlantDTO plantDTO) {
        return plantService.savePlant(plantDTO);
    }

    @GetMapping
    public PlantDTO getPlantByName(@RequestParam String plantName) {
        return plantService.getPlantByName(plantName);
    }
}
