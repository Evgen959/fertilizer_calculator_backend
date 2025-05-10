package org.example.model.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class PeriodVegetation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; // Уникальный идентификатор период вегетации
    private String periodVegetationName; // Название периода вегетации
    private int numberOfDays; // Период вегетации
    private String description; // Описание период вегетации

    @ManyToOne // Связь с plant
    @JoinColumn(name = "plant_id", nullable = false)
    // @NotNull: Это аннотация из Bean Validation API. Она применяется для проверки данных на уровне Java-кода (до сохранения в базу данных). Я думаю ее нужно использовать в DTO
    private Plant plant;

    @OneToMany(mappedBy = "periodVegetation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CalculatedOfFertilizer> calculatedOfFertilizers;

    private double CaO; // Химический элемент оксид кальция необходимый растению в данный период вегетации
    private double Mg; // Химический элемент сульфат магния необходимый растению в данный период вегетации
    private double N; // Химический элемент азот необходимый растению в данный период вегетации
    private double P; // Химический элемент фосфор необходимый растению в данный период вегетации
    private double K; // Химический элемент калий необходимый растению в данный период вегетации

    public PeriodVegetation() {
    }

    public PeriodVegetation(UUID id, String periodVegetationName, int numberOfDays, String description, Plant plant, List<CalculatedOfFertilizer> calculatedOfFertilizers, double caO, double mg, double n, double p, double k) {
        this.id = id;
        this.periodVegetationName = periodVegetationName;
        this.numberOfDays = numberOfDays;
        this.description = description;
        this.plant = plant;
        this.calculatedOfFertilizers = calculatedOfFertilizers;
        this.CaO = caO;
        this.Mg = mg;
        this.N = n;
        this.P = p;
        this.K = k;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPeriodVegetationName() {
        return periodVegetationName;
    }

    public void setPeriodVegetationName(String periodVegetationName) {
        this.periodVegetationName = periodVegetationName;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public List<CalculatedOfFertilizer> getCalculatedOfFertilizers() {
        return calculatedOfFertilizers;
    }

    public void setCalculatedOfFertilizers(List<CalculatedOfFertilizer> calculatedOfFertilizers) {
        this.calculatedOfFertilizers = calculatedOfFertilizers;
    }

    public double getCaO() {
        return CaO;
    }

    public void setCaO(double caO) {
        CaO = caO;
    }

    public double getMg() {
        return Mg;
    }

    public void setMg(double mg) {
        Mg = mg;
    }

    public double getN() {
        return N;
    }

    public void setN(double n) {
        N = n;
    }

    public double getP() {
        return P;
    }

    public void setP(double p) {
        P = p;
    }

    public double getK() {
        return K;
    }

    public void setK(double k) {
        K = k;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeriodVegetation that)) return false;
        return numberOfDays == that.numberOfDays && Double.compare(CaO, that.CaO) == 0 && Double.compare(Mg, that.Mg) == 0 && Double.compare(N, that.N) == 0 && Double.compare(P, that.P) == 0 && Double.compare(K, that.K) == 0 && Objects.equals(id, that.id) && Objects.equals(periodVegetationName, that.periodVegetationName) && Objects.equals(description, that.description) && Objects.equals(plant, that.plant) && Objects.equals(calculatedOfFertilizers, that.calculatedOfFertilizers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, periodVegetationName, numberOfDays, description, plant, calculatedOfFertilizers, CaO, Mg, N, P, K);
    }
}
