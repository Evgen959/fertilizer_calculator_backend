package org.example.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; // Уникальный идентификатор растения
    private String plantName; // Название растения
    private int numberOfDays; // Полный период вегетации
    private String description; // Описание растения
    private BigDecimal CaO; // Химический элемент оксид кальция необходимый для растения
    private BigDecimal Mg; // Химический элемент сульфат магния необходимый для растения
    private BigDecimal N; // Химический элемент азот необходимый для растения
    private BigDecimal P; // Химический элемент фосфор необходимый для растения
    private BigDecimal K; // Химический элемент калий необходимый для растения

    public Plant() {
    }

    public Plant(UUID id, String plantName, int numberOfDays, String description, BigDecimal caO, BigDecimal mg, BigDecimal n, BigDecimal p, BigDecimal k) {
        this.id = id;
        this.plantName = plantName;
        this.numberOfDays = numberOfDays;
        this.description = description;
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

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
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
        Plant plant = (Plant) o;
        return Objects.equals(id, plant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
