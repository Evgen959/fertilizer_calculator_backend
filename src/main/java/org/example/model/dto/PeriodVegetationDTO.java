package org.example.model.dto;

public record PeriodVegetationDTO(String periodVegetationName,
                                  int numberOfDays,
                                  String description,
                                  double CaO,
                                  double Mg,
                                  double N,
                                  double P,
                                  double K) {
}
