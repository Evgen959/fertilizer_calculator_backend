package org.example.service.interfeces;

import org.example.model.dto.FertilizerDTO;

public interface FertilizerService {

    FertilizerDTO saveFertilizer(FertilizerDTO fertilizerDTO);

    FertilizerDTO getFertilizerByName(String fertilizerName);
}
