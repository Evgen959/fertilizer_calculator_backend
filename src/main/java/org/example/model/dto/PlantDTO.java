package org.example.model.dto;

import org.example.model.entity.PeriodVegetation;

import java.util.Set;

public record PlantDTO(String plantName,
                       Set<PeriodVegetation> periodVegetation,
                       String description) {
}
