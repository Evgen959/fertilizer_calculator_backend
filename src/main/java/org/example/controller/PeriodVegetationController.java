package org.example.controller;

import org.example.model.dto.PeriodVegetationDTO;
import org.example.service.interfeces.PeriodVegetationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/period-vegetation")
public class PeriodVegetationController {

    private final PeriodVegetationService periodVegetation;

    public PeriodVegetationController(PeriodVegetationService periodVegetation) {
        this.periodVegetation = periodVegetation;
    }

    @PostMapping
    public PeriodVegetationDTO savePeriodVegetation(PeriodVegetationDTO periodVegetationDTO) {
        return periodVegetation.savePeriodVegetation(periodVegetationDTO);
    }

    @GetMapping
    public PeriodVegetationDTO getPeriodVegetationByName(String periodVegetationName) {
        return periodVegetation.getPeriodVegetationByName(periodVegetationName);
    }
}
