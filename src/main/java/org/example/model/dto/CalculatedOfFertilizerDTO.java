package org.example.model.dto;

import jakarta.validation.constraints.NotNull;
import org.example.model.entity.User;

import java.util.List;
import java.util.Set;

public record CalculatedOfFertilizerDTO(String calculatedName,
                                        String calculatedDescription,
                                        Set<String> fertilizerName,
                                        List<Double> weight,
                                        List<Double> calculationError) {
}
