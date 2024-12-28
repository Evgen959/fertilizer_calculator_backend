package org.example.service.mapping;

import org.example.model.dto.PlantDTO;
import org.example.model.entity.Plant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlantMapper {

    @Mapping(target = "id", ignore = true) // аннотация исключает введение пользователем id
    Plant mapDtoToEntity(PlantDTO dto);

    PlantDTO mapEntityToDto(Plant entity);

}
