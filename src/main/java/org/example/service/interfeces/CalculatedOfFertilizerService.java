package org.example.service.interfeces;

import org.example.model.dto.CalculatedOfFertilizerDTO;

public interface CalculatedOfFertilizerService {

    CalculatedOfFertilizerDTO saveCalculatedOfFertilizer(CalculatedOfFertilizerDTO calculatedOfFertilizerDTO);

    CalculatedOfFertilizerDTO getCalculatedOfFertilizerByName(String calculatedOfFertilizerName);
}
