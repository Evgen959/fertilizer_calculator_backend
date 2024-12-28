package org.example.service.mapping;

import org.example.model.dto.FertilizerDTO;
import org.example.model.entity.Fertilizer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FertilizerMapper {

    @Mapping(target = "id", ignore = true)
    Fertilizer mapDtoToEntity(FertilizerDTO dto);

    FertilizerDTO mapEntityToDto(Fertilizer entity);
}
