package org.example.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "app_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private final Set<User> users = new HashSet<>();

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return String.format("Roles : id - %s, name - %s]", id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role role)) return false;
        return Objects.equals(id, role.id) && Objects.equals(name, role.name) && Objects.equals(users, role.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, users);
    }
}
