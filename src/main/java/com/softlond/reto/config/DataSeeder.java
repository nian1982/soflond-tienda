package com.softlond.reto.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.softlond.reto.entity.UserEntity;
import com.softlond.reto.repository.UserRepository;

import reactor.core.publisher.Flux;

@Component
public class DataSeeder implements ApplicationRunner {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public DataSeeder(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        repository.count()
                .filter(count -> count == 0)
                .flatMapMany(count -> Flux.just(
                        createUser("admin", "admin", "ADMIN"),
                        createUser("user", "user", "USER")
                ))
                .flatMap(repository::save)
                .then()
                .subscribe();
    }

    private UserEntity createUser(String username, String password, String role) {
        return UserEntity.builder()
                .username(username)
                .password(encoder.encode(password))
                .roles(role)
                .active(true)
                .build();
    }
}
