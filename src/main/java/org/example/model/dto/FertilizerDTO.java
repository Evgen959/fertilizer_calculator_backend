package org.example.model.dto;

public record FertilizerDTO (String fertilizerName,
                             String description,
                             double CaO,
                             double Mg,
                             double N,
                             double P,
                             double K) {
}
