package org.example.repository;

import org.example.model.entity.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FertilizerRepository extends JpaRepository<Fertilizer, UUID> {

    Fertilizer findByFertilizerName(String fertilizerName);
}
