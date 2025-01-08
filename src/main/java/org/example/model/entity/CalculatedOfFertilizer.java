package org.example.model.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
public class CalculatedOfFertilizer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; // Уникальный идентификатор расчитаных удобрений

    private String calculatedOfFertilizerName;

    @ManyToMany
    @JoinTable(
            name = "calculatedOfFertilizer_plant", // имя связующей таблицы
            joinColumns = @JoinColumn(name = "calculatedOfFertilizer_id"), // колонка для связи с таблицей calculatedOfFertilizer
            inverseJoinColumns = @JoinColumn(name = "plant_id") // колонка для связи с таблицей plant
    )
    private Set<Plant> plant; // Растение

    @ManyToMany
    @JoinTable(
        name = "calculatedOfFertilizer_fertilizer", // имя связующей таблицы
        joinColumns = @JoinColumn(name = "calculatedOfFertilizer_id"), // колонка для связи с таблицей calculatedOfFertilizer
        inverseJoinColumns = @JoinColumn(name = "fertilizer_id") // колонка для связи с таблицей fertilizer
    )
    private Set<Fertilizer> fertilizer = new HashSet<>(); // Удобрение

    private int weight; // Рассчитанный вес удобрения

    @ManyToMany(mappedBy = "calculated")
    private final Set<User> users = new HashSet<>(); // Кто создал расчет удобрений

    @CreatedDate
    private LocalDateTime createdAt; // Дата внесения в БД расчета


    public CalculatedOfFertilizer(UUID id, String calculatedOfFertilizerName, Set<Plant> plant, Set<Fertilizer> fertilizer, int weight, LocalDateTime createdAt) {
        this.id = id;
        this.calculatedOfFertilizerName = calculatedOfFertilizerName;
        this.plant = plant;
        this.fertilizer = fertilizer;
        this.weight = weight;
        this.createdAt = createdAt;
    }

    public CalculatedOfFertilizer() {
    }

    public String getCalculatedOfFertilizerName() {
        return calculatedOfFertilizerName;
    }

    public void setCalculatedOfFertilizerName(String calculatedOfFertilizerName) {
        this.calculatedOfFertilizerName = calculatedOfFertilizerName;
    }

    public Set<Plant> getPlant() {
        return plant;
    }

    public void setPlant(Set<Plant> plant) {
        this.plant = plant;
    }

    public Set<Fertilizer> getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(Set<Fertilizer> fertilizer) {
        this.fertilizer = fertilizer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

   public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Set<User> getUsers() {
        return users;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CalculatedOfFertilizer that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
