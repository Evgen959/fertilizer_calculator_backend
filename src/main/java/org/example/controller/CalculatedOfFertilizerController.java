package org.example.controller;

import org.example.model.dto.CalculatedOfFertilizerDTO;
import org.example.service.interfeces.CalculatedOfFertilizerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculated-of-fertilizer")
public class CalculatedOfFertilizerController {

    private final CalculatedOfFertilizerService calculatedOfFertilizerService;

    public CalculatedOfFertilizerController(CalculatedOfFertilizerService calculatedOfFertilizerService) {
        this.calculatedOfFertilizerService = calculatedOfFertilizerService;
    }

    @PostMapping
    public CalculatedOfFertilizerDTO saveCalculatedOfFertilizer(CalculatedOfFertilizerDTO calculatedOfFertilizerDTO) {
        return calculatedOfFertilizerService.saveCalculatedOfFertilizer(calculatedOfFertilizerDTO);
    }

    @GetMapping
    public CalculatedOfFertilizerDTO getCalculatedOfFertilizerByName(String calculatedOfFertilizerName) {
        return calculatedOfFertilizerService.getCalculatedOfFertilizerByName(calculatedOfFertilizerName);
    }
}
