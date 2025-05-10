package org.example.model.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;

    private String password;

    private String email;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
            name = "user_roles", // имя связующей таблицы
            joinColumns = @JoinColumn(name = "user_id"), // колонка для связи с таблицей users
            inverseJoinColumns = @JoinColumn(name = "role_id") // колонка для связи с таблицей roles
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CalculatedOfFertilizer> calculated = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_fertilizer", // имя связующей таблицы
            joinColumns = @JoinColumn(name = "user_id"), // колонка для связи с таблицей users
            inverseJoinColumns = @JoinColumn(name = "fertilizer_id") // колонка для связи с таблицей roles
    )
    private Set<Fertilizer> fertilizers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_plant", // имя связующей таблицы
            joinColumns = @JoinColumn(name = "user_id"), // колонка для связи с таблицей user
            inverseJoinColumns = @JoinColumn(name = "plant_id") // колонка для связи с таблицей plant
    )
    private Set<Plant> plant; // Растение

    public User() {
    }

    public User(UUID id, String username, String password, String email, LocalDateTime createdAt, LocalDateTime updatedAt, Set<Role> roles, Set<CalculatedOfFertilizer> calculated, Set<Fertilizer> fertilizers, Set<Plant> plant) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.roles = roles;
        this.calculated = calculated;
        this.fertilizers = fertilizers;
        this.plant = plant;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<CalculatedOfFertilizer> getCalculated() {
        return calculated;
    }

    public void setCalculated(Set<CalculatedOfFertilizer> calculated) {
        this.calculated = calculated;
    }

    public Set<Fertilizer> getFertilizers() {
        return fertilizers;
    }

    public void setFertilizers(Set<Fertilizer> fertilizers) {
        this.fertilizers = fertilizers;
    }

    public Set<Plant> getPlant() {
        return plant;
    }

    public void setPlant(Set<Plant> plant) {
        this.plant = plant;
    }

    @Override
    public String toString() {
        return String.format("User : id - %s, username - %s", id, username);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
