package com.softlond.reto.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.stereotype.Service;

import com.softlond.reto.entity.UserEntity;
import com.softlond.reto.repository.UserRepository;

import reactor.core.publisher.Mono;

@Service
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return repository.findByUsername(username)
                .filter(UserEntity::isActive)
                .map(user -> User.withUsername(user.getUsername())
                        .password(user.getPassword())
                        .roles(user.getRoles().split(","))
                        .build()
                )
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found: " + username)));
    }
}
