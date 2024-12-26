package org.example.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserCreateDTO(@NotBlank(message = "username cannot be empty")
                            @Pattern(regexp = "^[A-Z][a-zA-Z0-9_-]{6,16}$", message = "username should be at least 6 characters long")
                            String username,
                            @NotBlank(message = "password cannot be empty")
                            @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$")
                            String password,
                            @Email
                            String email) {
}
