package org.example.service.mapping;

import org.example.model.dto.PeriodVegetationDTO;
import org.example.model.entity.PeriodVegetation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PeriodVegetationMapper {

    @Mapping(target = "id", ignore = true)
    PeriodVegetation mapDtoToEntity(PeriodVegetationDTO dto);

    PeriodVegetationDTO mapEntityToDto(PeriodVegetation entity);
}
