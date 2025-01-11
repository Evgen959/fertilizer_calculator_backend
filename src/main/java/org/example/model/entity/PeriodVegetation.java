package org.example.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
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

    private BigDecimal CaO; // Химический элемент оксид кальция необходимый растению в данный период вегетации
    private BigDecimal Mg; // Химический элемент сульфат магния необходимый растению в данный период вегетации
    private BigDecimal N; // Химический элемент азот необходимый растению в данный период вегетации
    private BigDecimal P; // Химический элемент фосфор необходимый растению в данный период вегетации
    private BigDecimal K; // Химический элемент калий необходимый растению в данный период вегетации

    public PeriodVegetation() {
    }

    public PeriodVegetation(UUID id, String periodVegetationName, int numberOfDays, String description, Plant plant, BigDecimal caO, BigDecimal mg, BigDecimal n, BigDecimal p, BigDecimal k) {
        this.id = id;
        this.periodVegetationName = periodVegetationName;
        this.numberOfDays = numberOfDays;
        this.description = description;
        this.plant = plant;
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

    public BigDecimal getCaO() {
        return CaO;
    }

    public void setCaO(BigDecimal caO) {
        CaO = caO;
    }

    public BigDecimal getMg() {
        return Mg;
    }

    public void setMg(BigDecimal mg) {
        Mg = mg;
    }

    public BigDecimal getN() {
        return N;
    }

    public void setN(BigDecimal n) {
        N = n;
    }

    public BigDecimal getP() {
        return P;
    }

    public void setP(BigDecimal p) {
        P = p;
    }

    public BigDecimal getK() {
        return K;
    }

    public void setK(BigDecimal k) {
        K = k;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodVegetation that = (PeriodVegetation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
