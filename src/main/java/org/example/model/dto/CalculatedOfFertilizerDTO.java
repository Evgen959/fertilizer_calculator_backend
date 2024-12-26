package org.example.model.dto;

import org.example.model.entity.Fertilizer;
import org.example.model.entity.Plant;
import org.example.model.entity.User;

import java.time.LocalDateTime;
import java.util.Set;

public record CalculatedOfFertilizerDTO(String calculatedName,
                                        Set<Plant> plant,
                                        Set<Fertilizer> fertilizer,
                                        int weight,
                                        Set<User> users,
                                        LocalDateTime createdAt) {
}
