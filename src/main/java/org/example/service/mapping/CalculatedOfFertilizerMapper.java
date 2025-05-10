package org.example.service.mapping;

import org.example.model.dto.CalculatedOfFertilizerDTO;
import org.example.model.entity.CalculatedOfFertilizer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CalculatedOfFertilizerMapper {

    @Mapping(target = "id", ignore = true)
    CalculatedOfFertilizer mapDtoToEntity(CalculatedOfFertilizerDTO dto);

    CalculatedOfFertilizerDTO mapEntityToDto(CalculatedOfFertilizer entity);
}
