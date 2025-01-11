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

    @ManyToMany(mappedBy = "fertilizers")
    private final Set<User> users = new HashSet<>();// поле для связи пользователя и удобрения

    private BigDecimal CaO; // Химический элемент оксид кальция входящий в состав удобрения
    private BigDecimal Mg; // Химический элемент сульфат магния входящий в состав удобрения
    private BigDecimal N; // Химический элемент азот входящий в состав удобрения
    private BigDecimal P; // Химический элемент фосфор входящий в состав удобрения
    private BigDecimal K; // Химический элемент калий входящий в состав удобрения

    public Fertilizer(UUID id, String fertilizerName, String description, BigDecimal caO, BigDecimal mg, BigDecimal n, BigDecimal p, BigDecimal k) {
        this.id = id;
        this.fertilizerName = fertilizerName;
        this.description = description;
        this.CaO = caO;
        this.Mg = mg;
        this.N = n;
        this.P = p;
        this.K = k;
    }

    public Fertilizer() {

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
        if (o == null || getClass() != o.getClass()) return false;
        Fertilizer that = (Fertilizer) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
