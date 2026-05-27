package com.softlond.reto.mapper;

import org.springframework.stereotype.Component;

import com.softlond.reto.dto.ProductoRequest;
import com.softlond.reto.dto.ProductoResponse;
import com.softlond.reto.entity.Producto;

@Component
public class ProductoMapper {

    public Producto toEntity(ProductoRequest request) {
        return Producto.builder()
                .name(request.name())
                .brand(request.brand())
                .description(request.description())
                .stock(request.stock())
                .price(request.price())
                .tiendaId(request.tiendaId())
                .build();
    }

    public void updateEntity(Producto producto, ProductoRequest request) {
        producto.setName(request.name());
        producto.setBrand(request.brand());
        producto.setDescription(request.description());
        producto.setStock(request.stock());
        producto.setPrice(request.price());
        producto.setTiendaId(request.tiendaId());
    }

    public ProductoResponse toResponse(Producto producto) {
        return new ProductoResponse(
                producto.getId(),
                producto.getName(),
                producto.getBrand(),
                producto.getDescription(),
                producto.getStock(),
                producto.getPrice(),
                producto.getTiendaId());
    }
}
