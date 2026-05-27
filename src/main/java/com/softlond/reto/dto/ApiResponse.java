package com.softlond.reto.dto;

import java.time.LocalDateTime;

public record ApiResponse<T>(
    boolean success,
    String message,
    T data,
    LocalDateTime timestamp,
    int status
) {
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, message, data, LocalDateTime.now(), 200);
    }

    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>(true, "Recurso creado exitosamente", data, LocalDateTime.now(), 201);
    }

    public static <T> ApiResponse<T> updated(T data) {
        return new ApiResponse<>(true, "Recurso actualizado exitosamente", data, LocalDateTime.now(), 200);
    }

    public static ApiResponse<Void> deleted() {
        return new ApiResponse<>(true, "Recurso eliminado exitosamente", null, LocalDateTime.now(), 200);
    }

    public static <T> ApiResponse<T> error(int status, String message) {
        return new ApiResponse<>(false, message, null, LocalDateTime.now(), status);
    }
}
