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
import com.softlond.reto.dto.ProductoRequest;
import com.softlond.reto.dto.ProductoResponse;
import com.softlond.reto.service.IProductoService;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    private final IProductoService service;

    public ProductoController(IProductoService service) {
        this.service = service;
    }

    @GetMapping
    public Mono<ResponseEntity<ApiResponse<List<ProductoResponse>>>> getActive() {
        return service.getActive()
                .collectList()
                .map(list -> ResponseEntity.ok(ApiResponse.success(list, "Productos obtenidos exitosamente")));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ApiResponse<ProductoResponse>>> getById(@PathVariable Long id) {
        return service.read(id)
                .map(data -> ResponseEntity.ok(ApiResponse.success(data, "Producto obtenido exitosamente")));
    }

    @GetMapping("/tienda/{tiendaId}")
    public Mono<ResponseEntity<ApiResponse<List<ProductoResponse>>>> getByTiendaId(@PathVariable Long tiendaId) {
        return service.getByTiendaId(tiendaId)
                .collectList()
                .map(list -> ResponseEntity.ok(ApiResponse.success(list, "Productos obtenidos exitosamente")));
    }

    @PostMapping
    public Mono<ResponseEntity<ApiResponse<ProductoResponse>>> create(@Valid @RequestBody ProductoRequest request) {
        return service.create(request)
                .map(data -> ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(data)));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ApiResponse<ProductoResponse>>> update(@PathVariable Long id,
            @Valid @RequestBody ProductoRequest request) {
        return service.update(request, id)
                .map(data -> ResponseEntity.ok(ApiResponse.updated(data)));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<ApiResponse<Void>>> delete(@PathVariable Long id) {
        return service.delete(id)
                .then(Mono.just(ResponseEntity.ok(ApiResponse.deleted())));
    }
}
