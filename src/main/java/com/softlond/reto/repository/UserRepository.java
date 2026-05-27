package com.softlond.reto.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.softlond.reto.entity.UserEntity;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long> {

    @Query("SELECT * FROM softlond.users WHERE username = $1")
    Mono<UserEntity> findByUsername(String username);
}
