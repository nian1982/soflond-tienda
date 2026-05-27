package com.softlond.reto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.softlond.reto.dto.ProductoRequest;
import com.softlond.reto.dto.ProductoResponse;
import com.softlond.reto.service.IProductoService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    private final IProductoService service;

    public ProductoController(IProductoService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<ProductoResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/activos")
    public Flux<ProductoResponse> getActive() {
        return service.getActive();
    }

    @GetMapping("/{id}")
    public Mono<ProductoResponse> getById(@PathVariable Long id) {
        return service.read(id);
    }

    @GetMapping("/tienda/{tiendaId}")
    public Flux<ProductoResponse> getByTiendaId(@PathVariable Long tiendaId) {
        return service.getByTiendaId(tiendaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductoResponse> create(@Valid @RequestBody ProductoRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public Mono<ProductoResponse> update(@PathVariable Long id, @Valid @RequestBody ProductoRequest request) {
        return service.update(request, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
