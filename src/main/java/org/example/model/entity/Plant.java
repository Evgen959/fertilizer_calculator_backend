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

    @ManyToMany
    @JoinTable(
            name = "plant_periodVegetation", // имя связующей таблицы
            joinColumns = @JoinColumn(name = "plant_id"), // колонка для связи с таблицей plant
            inverseJoinColumns = @JoinColumn(name = "periodVegetation_id") // колонка для связи с таблицей periodVegetation
    )
    private Set<PeriodVegetation> periodVegetation = new HashSet<>(); // Период вегетации

    private String description; // Описание растения

    @ManyToMany(mappedBy = "plant") // Связь с CalculatedOfFertilizer
    private Set<CalculatedOfFertilizer> calculatedOfFertilizer = new HashSet<>();

    public Plant() {
    }

    public Plant(UUID id, String plantName, Set<PeriodVegetation> periodVegetation, String description) {
        this.id = id;
        this.plantName = plantName;
        this.periodVegetation = periodVegetation;
        this.description = description;
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

    public Set<CalculatedOfFertilizer> getCalculatedOfFertilizer() {
        return calculatedOfFertilizer;
    }

    public void setCalculatedOfFertilizer(Set<CalculatedOfFertilizer> calculatedOfFertilizer) {
        this.calculatedOfFertilizer = calculatedOfFertilizer;
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
