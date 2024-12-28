package org.example.controller;

import org.example.model.dto.FertilizerDTO;
import org.example.service.interfeces.FertilizerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fertilizer")
public class FertilizerController {

    private final FertilizerService fertilizerService;

    public FertilizerController(FertilizerService fertilizerService) {
        this.fertilizerService = fertilizerService;
    }

    @PostMapping
    public FertilizerDTO saveFertilizer(@RequestBody FertilizerDTO fertilizerDTO) {
        return fertilizerService.saveFertilizer(fertilizerDTO);
    }

    @GetMapping
    public FertilizerDTO getFertilizerByName(@RequestParam String fertilizerName) {
        return fertilizerService.getFertilizerByName(fertilizerName);
    }
}
