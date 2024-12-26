package org.example.repository;

import org.example.model.entity.CalculatedOfFertilizer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CalculatedOfFertilizerRepository extends JpaRepository<CalculatedOfFertilizer, UUID> {
}
