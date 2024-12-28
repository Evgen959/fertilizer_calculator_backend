package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.dto.PeriodVegetationDTO;
import org.example.model.entity.PeriodVegetation;
import org.example.repository.PeriodVegetationRepository;
import org.example.service.interfeces.PeriodVegetationService;
import org.example.service.mapping.PeriodVegetationMapper;
import org.springframework.stereotype.Service;

@Service
public class PeriodVegetationServiceImpl implements PeriodVegetationService {

    private final PeriodVegetationRepository repository;
    private final PeriodVegetationMapper mapper;

    public PeriodVegetationServiceImpl(PeriodVegetationRepository repository, PeriodVegetationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public PeriodVegetationDTO savePeriodVegetation(PeriodVegetationDTO periodVegetationDTO) {
        PeriodVegetation periodVegetation = mapper.mapDtoToEntity(periodVegetationDTO);
        return mapper.mapEntityToDto(repository.save(periodVegetation));
    }

    @Override
    public PeriodVegetationDTO getPeriodVegetationByName(String periodVegetationName) {
        return mapper.mapEntityToDto(repository.findPeriodVegetationByPeriodVegetationName(periodVegetationName));
    }
}
