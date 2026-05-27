package com.softlond.reto.mapper;

import org.springframework.stereotype.Component;

import com.softlond.reto.dto.TiendaRequest;
import com.softlond.reto.dto.TiendaResponse;
import com.softlond.reto.entity.Tienda;

@Component
public class TiendaMapper {

    public Tienda toEntity(TiendaRequest request) {
        return Tienda.builder()
                .name(request.name())
                .description(request.description())
                .active(true)
                .build();
    }

    public void updateEntity(Tienda entity, TiendaRequest request) {
        entity.setName(request.name());
        entity.setDescription(request.description());
    }

    public TiendaResponse toResponse(Tienda entity) {
        return new TiendaResponse(entity.getId(), entity.getName(), entity.getDescription());
    }
}
