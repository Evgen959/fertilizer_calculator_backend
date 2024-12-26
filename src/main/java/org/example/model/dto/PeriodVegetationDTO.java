package org.example.model.dto;

import java.math.BigDecimal;

public record PeriodVegetationDTO(String periodVegetationName,
                                  int numberOfDays,
                                  String description,
                                  BigDecimal CaO,
                                  BigDecimal Mg,
                                  BigDecimal N,
                                  BigDecimal P,
                                  BigDecimal K) {
}
