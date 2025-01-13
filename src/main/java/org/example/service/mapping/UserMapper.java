package org.example.service.mapping;

import org.example.model.dto.UserCreateDTO;
import org.example.model.dto.UserResponseDTO;
import org.example.model.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User mapDtoToEntity(UserCreateDTO dto){
        User entity = new User();
        entity.setUsername(dto.username());
        entity.setPassword(dto.password());
        entity.setEmail(dto.email());
        return entity;
    }

    public UserResponseDTO mapEntityToDto(User entity){
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }
}
