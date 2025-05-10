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

    @ManyToOne
    @JoinColumn(name = "calculatedOfFertilizer_id", nullable = false)
    private CalculatedOfFertilizer calculatedOfFertilizer;

    @ManyToMany(mappedBy = "fertilizers")
    private final Set<User> users = new HashSet<>();// поле для связи пользователя и удобрения

    private double CaO; // Химический элемент оксид кальция входящий в состав удобрения
    private double Mg; // Химический элемент сульфат магния входящий в состав удобрения
    private double N; // Химический элемент азот входящий в состав удобрения
    private double P; // Химический элемент фосфор входящий в состав удобрения
    private double K; // Химический элемент калий входящий в состав удобрения
    public Fertilizer() {

    }

    public Fertilizer(UUID id, String fertilizerName, String description, CalculatedOfFertilizer calculatedOfFertilizer, double caO, double mg, double n, double p, double k) {
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFertilizerName() {
        return fertilizerName;
    }

    public void setFertilizerName(String fertilizerName) {
        this.fertilizerName = fertilizerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CalculatedOfFertilizer getCalculatedOfFertilizer() {
        return calculatedOfFertilizer;
    }

    public void setCalculatedOfFertilizer(CalculatedOfFertilizer calculatedOfFertilizer) {
        this.calculatedOfFertilizer = calculatedOfFertilizer;
    }

    public Set<User> getUsers() {
        return users;
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
        if (!(o instanceof Fertilizer that)) return false;
        return Double.compare(CaO, that.CaO) == 0 && Double.compare(Mg, that.Mg) == 0 && Double.compare(N, that.N) == 0 && Double.compare(P, that.P) == 0 && Double.compare(K, that.K) == 0 && Objects.equals(id, that.id) && Objects.equals(fertilizerName, that.fertilizerName) && Objects.equals(description, that.description) && Objects.equals(calculatedOfFertilizer, that.calculatedOfFertilizer) && Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fertilizerName, description, calculatedOfFertilizer, users, CaO, Mg, N, P, K);
    }
}
