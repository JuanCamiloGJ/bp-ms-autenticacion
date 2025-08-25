package com.crediya.r2dbc;

import reactor.core.publisher.Mono;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.crediya.r2dbc.entity.UserEntity;

public interface UserManagerRepository extends ReactiveCrudRepository<UserEntity, Long>, ReactiveQueryByExampleExecutor<UserEntity> {

    Mono<UserEntity> findByDocumentTypeAndDocumentNumber(String documentType, String documentNumber);

    @Query("SELECT email FROM users WHERE email = :email")
    Mono<String> findByEmail(String email);
}
