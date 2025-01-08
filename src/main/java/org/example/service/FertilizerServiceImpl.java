package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.dto.FertilizerDTO;
import org.example.model.entity.Fertilizer;
import org.example.repository.FertilizerRepository;
import org.example.service.interfeces.FertilizerService;
import org.example.service.mapping.FertilizerMapper;
import org.springframework.stereotype.Service;

@Service
public class FertilizerServiceImpl implements FertilizerService {

    private final FertilizerRepository repository;
    private final FertilizerMapper mapper;

    public FertilizerServiceImpl(FertilizerRepository repository, FertilizerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public FertilizerDTO saveFertilizer(FertilizerDTO fertilizerDTO) {
        Fertilizer fertilizer = mapper.mapDtoToEntity(fertilizerDTO);
        return mapper.mapEntityToDto(repository.save(fertilizer));
    }

    @Override
    public FertilizerDTO getFertilizerByName(String fertilizerName) {
        return mapper.mapEntityToDto(repository.findByFertilizerName(fertilizerName));
    }
}
