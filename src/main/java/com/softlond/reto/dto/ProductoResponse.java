package com.softlond.reto.dto;

import java.math.BigDecimal;

public record ProductoResponse(
    Long id,
    String name,
    String brand,
    String description,
    Integer stock,
    BigDecimal price,
    Long tiendaId
) {
}
