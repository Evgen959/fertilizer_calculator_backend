package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.dto.CalculatedOfFertilizerDTO;
import org.example.model.entity.CalculatedOfFertilizer;
import org.example.repository.CalculatedOfFertilizerRepository;
import org.example.service.interfeces.CalculatedOfFertilizerService;
import org.example.service.mapping.CalculatedOfFertilizerMapper;
import org.springframework.stereotype.Service;

@Service
public class CalculatedOfFertilizerServiceImpl implements CalculatedOfFertilizerService {

    private final CalculatedOfFertilizerRepository repository;
    private final CalculatedOfFertilizerMapper mapper;

    public CalculatedOfFertilizerServiceImpl(CalculatedOfFertilizerRepository repository, CalculatedOfFertilizerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public CalculatedOfFertilizerDTO saveCalculatedOfFertilizer(CalculatedOfFertilizerDTO calculatedOfFertilizerDTO) {
        CalculatedOfFertilizer calculatedOfFertilizer = mapper.mapDtoToEntity(calculatedOfFertilizerDTO);
        return mapper.mapEntityToDto(repository.save(calculatedOfFertilizer));
    }

    @Override
    public CalculatedOfFertilizerDTO getCalculatedOfFertilizerByName(String calculatedOfFertilizerName) {
        return mapper.mapEntityToDto(repository.findCalculatedOfFertilizerByCalculatedOfFertilizerName(calculatedOfFertilizerName));
    }
}
