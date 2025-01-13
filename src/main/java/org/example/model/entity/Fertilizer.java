package org.example.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
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

    private BigDecimal CaO; // Химический элемент оксид кальция входящий в состав удобрения
    private BigDecimal Mg; // Химический элемент сульфат магния входящий в состав удобрения
    private BigDecimal N; // Химический элемент азот входящий в состав удобрения
    private BigDecimal P; // Химический элемент фосфор входящий в состав удобрения
    private BigDecimal K; // Химический элемент калий входящий в состав удобрения
    public Fertilizer() {

    }

    public Fertilizer(UUID id, String fertilizerName, String description, CalculatedOfFertilizer calculatedOfFertilizer, BigDecimal caO, BigDecimal mg, BigDecimal n, BigDecimal p, BigDecimal k) {
        this.id = id;
        this.fertilizerName = fertilizerName;
        this.description = description;
        this.calculatedOfFertilizer = calculatedOfFertilizer;
        CaO = caO;
        Mg = mg;
        N = n;
        P = p;
        K = k;
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
        if (!(o instanceof Fertilizer that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(fertilizerName, that.fertilizerName) && Objects.equals(description, that.description) && Objects.equals(calculatedOfFertilizer, that.calculatedOfFertilizer) && Objects.equals(users, that.users) && Objects.equals(CaO, that.CaO) && Objects.equals(Mg, that.Mg) && Objects.equals(N, that.N) && Objects.equals(P, that.P) && Objects.equals(K, that.K);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fertilizerName, description, calculatedOfFertilizer, users, CaO, Mg, N, P, K);
    }
}
