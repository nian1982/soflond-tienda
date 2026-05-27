package com.softlond.reto.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.softlond.reto.entity.Tienda;

public interface ITiendaRepository extends ReactiveCrudRepository<Tienda, Long> {

}
