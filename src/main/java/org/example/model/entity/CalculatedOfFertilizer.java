package org.example.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

public class CalculatedOfFertilizer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; // Уникальный идентификатор расчитаных удобрений
    private Fertilizer fertilizer; // Удобрение
    private int weight; // Вес или количество удобрения
    private Plant plant; // Растение
    private PeriodVegetation periodVegetation; // Период вегетации
//    created_by UUID [not null, ref: > users.id] // Кто создал расчет удобрений
    @CreatedDate
    private LocalDateTime createdAt; // Дата внесения в БД расчета


}
