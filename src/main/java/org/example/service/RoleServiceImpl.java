package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.model.entity.Role;
import org.example.repository.RoleRepository;
import org.example.service.interfeces.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    private static String DEFAULT_ROLE_NAME = "USER";

    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role getRoleByIdOrThrow(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role with id " + id + " not found"));
    }

    @Override
    public Role getRoleByNameOrThrow(String name) {
        return getRoleByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Role with name " + name + " not found"));
    }

    private Optional<Role> getRoleByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }

    @Override
    public Role getInitRole() {
        // Здесь используется orElseGet, чтобы saveRole вызывался только если getRoleByName вернул Optional.empty()
        Role role = getRoleByName(DEFAULT_ROLE_NAME).orElseGet(() -> seveRole(new Role(DEFAULT_ROLE_NAME)));

        //  найди роль по имени роли по умолчанию
        // верни эту роль если она найдена, если нет, то создай ее с именем по умолчанию, сохрани и верни сохраненную роль
        return role;
    }

    private Role seveRole(Role role) {
        return repository.save(role);
    }
}
