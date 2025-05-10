package org.example.service.interfeces;

import org.example.model.dto.UserCreateDTO;
import org.example.model.dto.UserResponseDTO;

import java.util.UUID;

public interface UserService {

    UserResponseDTO saveUser(UserCreateDTO createUserDto);

    UserResponseDTO getUserByID(UUID id);
}
