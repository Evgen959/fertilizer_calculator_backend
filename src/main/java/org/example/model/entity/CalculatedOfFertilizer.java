package org.example.model.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class CalculatedOfFertilizer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; // Уникальный идентификатор посчитанных удобрений

    private String calculatedName; // название расчета

    private String calculatedDescription; // описание

    @OneToMany(mappedBy = "calculatedOfFertilizer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Fertilizer> fertilizer = new HashSet<>(); // название удобрений

    @ManyToOne
    @JoinColumn(name = "periodVegetation_id", nullable = false)
    private PeriodVegetation periodVegetation;

    @ElementCollection
    private List<Double> weight = new ArrayList<>(); // Посчитанный вес удобрений

    @ElementCollection
    private List<Double> calculationError = new ArrayList<>(); // погрешности расчета

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    // @NotNull: Это аннотация из Bean Validation API. Она применяется для проверки данных на уровне Java-кода (до сохранения в базу данных). Я думаю ее нужно использовать в DTO
    private User user; // Кто создал расчет удобрений

    public CalculatedOfFertilizer() {
    }

    public CalculatedOfFertilizer(UUID id, String calculatedName, String calculatedDescription, Set<Fertilizer> fertilizer, PeriodVegetation periodVegetation, List<Double> weight, List<Double> calculationError, User user) {
        this.id = id;
        this.calculatedName = calculatedName;
        this.calculatedDescription = calculatedDescription;
        this.fertilizer = fertilizer;
        this.periodVegetation = periodVegetation;
        this.weight = weight;
        this.calculationError = calculationError;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCalculatedName() {
        return calculatedName;
    }

    public void setCalculatedName(String calculatedName) {
        this.calculatedName = calculatedName;
    }

    public String getCalculatedDescription() {
        return calculatedDescription;
    }

    public void setCalculatedDescription(String calculatedDescription) {
        this.calculatedDescription = calculatedDescription;
    }

    public Set<Fertilizer> getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(Set<Fertilizer> fertilizer) {
        this.fertilizer = fertilizer;
    }

    public PeriodVegetation getPeriodVegetation() {
        return periodVegetation;
    }

    public void setPeriodVegetation(PeriodVegetation periodVegetation) {
        this.periodVegetation = periodVegetation;
    }

    public List<Double> getWeight() {
        return weight;
    }

    public void setWeight(List<Double> weight) {
        this.weight = weight;
    }

    public List<Double> getCalculationError() {
        return calculationError;
    }

    public void setCalculationError(List<Double> calculationError) {
        this.calculationError = calculationError;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CalculatedOfFertilizer that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(calculatedName, that.calculatedName) && Objects.equals(calculatedDescription, that.calculatedDescription) && Objects.equals(fertilizer, that.fertilizer) && Objects.equals(periodVegetation, that.periodVegetation) && Objects.equals(weight, that.weight) && Objects.equals(calculationError, that.calculationError) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, calculatedName, calculatedDescription, fertilizer, periodVegetation, weight, calculationError, user);
    }
}
