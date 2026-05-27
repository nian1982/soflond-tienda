package com.softlond.reto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softlond.reto.dto.ApiResponse;
import com.softlond.reto.dto.TiendaRequest;
import com.softlond.reto.dto.TiendaResponse;
import com.softlond.reto.service.TiendaService;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tienda")
public class TiendaController {

    private final TiendaService service;

    public TiendaController(TiendaService service) {
        this.service = service;
    }

    @GetMapping
    public Mono<ResponseEntity<ApiResponse<List<TiendaResponse>>>> getAll() {
        return service.getAll()
                .collectList()
                .map(list -> ResponseEntity.ok(ApiResponse.success(list, "Tiendas obtenidas exitosamente")));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ApiResponse<TiendaResponse>>> getById(@PathVariable Long id) {
        return service.read(id)
                .map(data -> ResponseEntity.ok(ApiResponse.success(data, "Tienda obtenida exitosamente")));
    }

    @PostMapping
    public Mono<ResponseEntity<ApiResponse<TiendaResponse>>> create(@Valid @RequestBody TiendaRequest request) {
        return service.create(request)
                .map(data -> ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(data)));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ApiResponse<TiendaResponse>>> update(@PathVariable Long id,
            @Valid @RequestBody TiendaRequest request) {
        return service.update(request, id)
                .map(data -> ResponseEntity.ok(ApiResponse.updated(data)));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<ApiResponse<Void>>> delete(@PathVariable Long id) {
        return service.delete(id)
                .then(Mono.just(ResponseEntity.ok(ApiResponse.deleted())));
    }
}
