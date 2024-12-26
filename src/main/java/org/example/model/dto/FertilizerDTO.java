package org.example.model.dto;

import java.math.BigDecimal;

public record FertilizerDTO (String fertilizerName,
                             String description,
                             BigDecimal CaO,
                             BigDecimal Mg,
                             BigDecimal N,
                             BigDecimal P,
                             BigDecimal K) {
}
