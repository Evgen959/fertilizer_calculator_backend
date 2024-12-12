package org.example.controller;

import org.example.model.dto.UserCreateDTO;
import org.example.model.dto.UserResponseDTO;
import org.example.service.interfeces.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDTO saveUser(@RequestBody UserCreateDTO createUserDto) {
        return userService.saveUser(createUserDto);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserByID(@PathVariable UUID id) {
        return userService.getUserByID(id);
    }
}
