package org.example.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;


@Entity
public class Fertilizer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;// Уникальный идентификатор удобрения
    private String fertilizerName; // Название удобрения
    private String description; // Описание удобрения

    @ManyToMany(mappedBy = "fertilizer") // Связь с CalculatedOfFertilizer
    private Set<CalculatedOfFertilizer> calculatedOfFertilizer = new HashSet<>();

    private double CaO; // Химический элемент оксид кальция входящий в состав удобрения
    private double Mg; // Химический элемент сульфат магния входящий в состав удобрения
    private double N; // Химический элемент азот входящий в состав удобрения
    private double P; // Химический элемент фосфор входящий в состав удобрения
    private double K; // Химический элемент калий входящий в состав удобрения

    public Fertilizer(UUID id, String fertilizerName, String description, Set<CalculatedOfFertilizer> calculatedOfFertilizer, double caO, double mg, double n, double p, double k) {
        this.id = id;
        this.fertilizerName = fertilizerName;
        this.description = description;
        this.calculatedOfFertilizer = calculatedOfFertilizer;
        this.CaO = caO;
        this.Mg = mg;
        this.N = n;
        this.P = p;
        this.K = k;
    }

    public Fertilizer() {

    }

    public Set<CalculatedOfFertilizer> getCalculatedOfFertilizer() {
        return calculatedOfFertilizer;
    }

    public void setCalculatedOfFertilizer(Set<CalculatedOfFertilizer> calculatedOfFertilizer) {
        this.calculatedOfFertilizer = calculatedOfFertilizer;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFertilizerName() {
        return fertilizerName;
    }

    public void setFertilizerName(String fertilizerName) {
        this.fertilizerName = fertilizerName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fertilizer that = (Fertilizer) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
