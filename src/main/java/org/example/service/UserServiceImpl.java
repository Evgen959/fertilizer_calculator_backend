package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.dto.UserCreateDTO;
import org.example.model.dto.UserResponseDTO;
import org.example.model.entity.User;
import org.example.repository.UserRepository;
import org.example.service.interfeces.UserService;
import org.example.service.mapping.UserMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public UserResponseDTO saveUser(UserCreateDTO createUserDto) {
        User user = mapper.mapDtoToEntity(createUserDto);
        return mapper.mapEntityToDto(repository.save(user));
    }

    @Override
    public UserResponseDTO getUserByID(UUID id) {
        User user = repository.findById(id).orElse(null);

        if (user == null) {
            return null;
        }
        return mapper.mapEntityToDto(user);
    }
}
