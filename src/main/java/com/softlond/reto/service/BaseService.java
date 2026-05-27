package com.softlond.reto.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BaseService<RQ, RS, ID> {

    Mono<RS> create(RQ request);

    Mono<RS> read(ID id);

    Mono<RS> update(RQ request, ID id);

    Mono<Void> delete(ID id);

    Flux<RS> getAll();

}
