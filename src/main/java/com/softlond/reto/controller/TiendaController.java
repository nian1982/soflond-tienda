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

import com.softlond.reto.dto.TiendaRequest;
import com.softlond.reto.dto.TiendaResponse;
import com.softlond.reto.service.TiendaService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tienda")
public class TiendaController {

    private final TiendaService service;

    public TiendaController(TiendaService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<TiendaResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Mono<TiendaResponse> getById(@PathVariable Long id) {
        return service.read(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TiendaResponse> create(@Valid @RequestBody TiendaRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public Mono<TiendaResponse> update(@PathVariable Long id, @Valid @RequestBody TiendaRequest request) {
        return service.update(request, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
