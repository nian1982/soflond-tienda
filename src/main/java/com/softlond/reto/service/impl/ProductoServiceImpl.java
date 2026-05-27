package com.softlond.reto.service.impl;

import org.springframework.stereotype.Service;

import com.softlond.reto.dto.ProductoRequest;
import com.softlond.reto.dto.ProductoResponse;
import com.softlond.reto.mapper.ProductoMapper;
import com.softlond.reto.repository.IProductoRepository;
import com.softlond.reto.service.IProductoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoServiceImpl implements IProductoService {

    private final IProductoRepository repository;
    private final ProductoMapper mapper;

    public ProductoServiceImpl(IProductoRepository repository, ProductoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<ProductoResponse> create(ProductoRequest request) {
        return repository.save(mapper.toEntity(request))
                .map(mapper::toResponse);
    }

    @Override
    public Mono<ProductoResponse> read(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse);
    }

    @Override
    public Mono<ProductoResponse> update(ProductoRequest request, Long id) {
        return repository.findById(id)
                .flatMap(entity -> {
                    mapper.updateEntity(entity, request);
                    return repository.save(entity);
                })
                .map(mapper::toResponse);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return repository.deleteById(id);
    }

    @Override
    public Flux<ProductoResponse> getAll() {
        return repository.findAll()
                .map(mapper::toResponse);
    }

    @Override
    public Flux<ProductoResponse> getByTiendaId(Long tiendaId) {
        return repository.findByTiendaId(tiendaId)
                .map(mapper::toResponse);
    }

    @Override
    public Mono<ProductoResponse> findByName(String name) {
        return repository.findByName(name)
                .map(mapper::toResponse);
    }
    
    @Override
    public Flux<ProductoResponse> getActive() {
        return repository.findAllActive(true)
                .map(mapper::toResponse);
    }
}
