package com.softlond.reto.service;
import com.softlond.reto.dto.ProductoRequest;
import com.softlond.reto.dto.ProductoResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductoService extends BaseService<ProductoRequest, ProductoResponse, Long> {
    Mono<ProductoResponse> findByName(String name);
    Flux<ProductoResponse> getActive();
    Flux<ProductoResponse> getByTiendaId(Long tiendaId);
}
