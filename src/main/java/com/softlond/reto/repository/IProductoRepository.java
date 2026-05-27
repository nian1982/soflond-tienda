package com.softlond.reto.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.softlond.reto.entity.Producto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductoRepository extends ReactiveCrudRepository<Producto, Long> {

    @Query("SELECT * FROM softlond.productos WHERE tienda_id = $1")
    Flux<Producto> findByTiendaId(Long tiendaId);

    @Query("SELECT * FROM softlond.productos where name = $1")
    Mono<Producto> findByName(String name);

    @Query("SELECT * FROM softlond.productos WHERE active = $1")
    Flux<Producto> findAllActive(boolean active);
}
