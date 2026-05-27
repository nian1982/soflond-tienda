package com.softlond.reto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProductoRequest(
    @NotBlank String name,
    String brand,
    String description,
    Integer stock,
    BigDecimal price,
    @NotNull Long tiendaId
) {
}
