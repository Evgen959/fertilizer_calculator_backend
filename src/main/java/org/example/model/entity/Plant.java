package org.example.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; // Уникальный идентификатор растения
    private String plantName; // Название растения

    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PeriodVegetation> periodVegetation = new HashSet<>(); // Период вегетации

    private String description; // Описание растения

    @ManyToMany(mappedBy = "plant") // Связь с user
    private Set<User> user = new HashSet<>();

    public Plant() {
    }

    public Plant(UUID id, String plantName, Set<PeriodVegetation> periodVegetation, String description, Set<User> user) {
        this.id = id;
        this.plantName = plantName;
        this.periodVegetation = periodVegetation;
        this.description = description;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public Set<PeriodVegetation> getPeriodVegetation() {
        return periodVegetation;
    }

    public void setPeriodVegetation(Set<PeriodVegetation> periodVegetation) {
        this.periodVegetation = periodVegetation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return Objects.equals(id, plant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
