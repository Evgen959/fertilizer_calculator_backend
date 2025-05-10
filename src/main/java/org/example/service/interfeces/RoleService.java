package org.example.service.interfeces;

import org.example.model.entity.Role;

import java.util.UUID;

public interface RoleService {

    Role getRoleByIdOrThrow(UUID id);

    Role getRoleByNameOrThrow(String name);

    Role getInitRole();
}
