package org.example.repository;

import org.example.model.entity.PeriodVegetation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PeriodVegetationRepository extends JpaRepository<PeriodVegetation, UUID> {

    PeriodVegetation findPeriodVegetationByPeriodVegetationName(String periodVegetationName);
}
