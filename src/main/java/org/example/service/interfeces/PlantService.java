package org.example.service.interfeces;

import org.example.model.dto.PlantDTO;

public interface PlantService {

    PlantDTO savePlant(PlantDTO plantDTO);

    PlantDTO getPlantByName(String plantName);
}
