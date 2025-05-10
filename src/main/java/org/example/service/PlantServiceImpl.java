package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.dto.PlantDTO;
import org.example.model.entity.Plant;
import org.example.repository.PlantRepository;
import org.example.service.interfeces.PlantService;
import org.example.service.mapping.PlantMapper;
import org.springframework.stereotype.Service;

@Service
public class PlantServiceImpl implements PlantService {

    private final PlantRepository repository;
    private final PlantMapper mapper;

    public PlantServiceImpl(PlantRepository repository, PlantMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public PlantDTO savePlant(PlantDTO plantDTO) {
        Plant plant = mapper.mapDtoToEntity(plantDTO);
        return mapper.mapEntityToDto(repository.save(plant));
    }

    @Override
    public PlantDTO getPlantByName(String plantName) {
        return mapper.mapEntityToDto(repository.findPlantByPlantName(plantName));
    }
}
