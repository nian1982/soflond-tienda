package com.softlond.reto.service;

import org.springframework.stereotype.Service;

import com.softlond.reto.dto.TiendaRequest;
import com.softlond.reto.dto.TiendaResponse;
import com.softlond.reto.exception.ResourceNotFoundException;
import com.softlond.reto.mapper.TiendaMapper;
import com.softlond.reto.repository.ITiendaRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TiendaService implements BaseService<TiendaRequest, TiendaResponse, Long> {

    private final ITiendaRepository repository;
    private final TiendaMapper mapper;

    public TiendaService(ITiendaRepository repository, TiendaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<TiendaResponse> create(TiendaRequest request) {
        return repository.save(mapper.toEntity(request))
                .map(mapper::toResponse);
    }

    @Override
    public Mono<TiendaResponse> read(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Tienda", id)))
                .map(mapper::toResponse);
    }

    @Override
    public Mono<TiendaResponse> update(TiendaRequest request, Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Tienda", id)))
                .flatMap(entity -> {
                    mapper.updateEntity(entity, request);
                    return repository.save(entity);
                })
                .map(mapper::toResponse);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Tienda", id)))
                .flatMap(entity -> {
                    entity.setActive(false);
                    return repository.save(entity);
                })
                .then();
    }

    @Override
    public Flux<TiendaResponse> getAll() {
        return repository.findAll()
                .map(mapper::toResponse);
    }
}
