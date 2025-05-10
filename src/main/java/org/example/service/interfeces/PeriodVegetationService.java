package org.example.service.interfeces;

import org.example.model.dto.PeriodVegetationDTO;

public interface PeriodVegetationService {

    PeriodVegetationDTO savePeriodVegetation(PeriodVegetationDTO periodVegetationDTO);

    PeriodVegetationDTO getPeriodVegetationByName(String periodVegetationName);
}
