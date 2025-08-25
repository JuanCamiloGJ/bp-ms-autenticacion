package com.crediya.r2dbc;

import java.time.LocalDate;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import com.crediya.model.user.User;
import com.crediya.model.user.exceptions.UserAnyException;
import com.crediya.model.user.gateways.UserRepository;
import com.crediya.r2dbc.entity.UserEntity;
import com.crediya.r2dbc.helper.ReactiveAdapterOperations;

@Repository
@Slf4j
public class UserRepositoryAdapter extends ReactiveAdapterOperations<
        User,
        UserEntity,
        Long,
        UserManagerRepository
        > implements UserRepository {
    public UserRepositoryAdapter(UserManagerRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, User.class));
    }

    @Override
    protected UserEntity toData(User entity) {
        var userEntity = super.toData(entity);
        userEntity.setBirthdate(entity.getBirthdate() != null ? LocalDate.parse(entity.getBirthdate()) : null);
        return userEntity;
    }

    @Override
    protected User toEntity(UserEntity data) {
        var user = super.toEntity(data);
        user.setBirthdate(data.getBirthdate() != null ? data.getBirthdate().toString() : null);
        return user;
    }

    @Override
    public Mono<Boolean> userExists(String documentType, String documentNumber) {
        return this.repository.findByDocumentTypeAndDocumentNumber(documentType, documentNumber)
                .onErrorResume(e -> {
                    log.error("Error checking if user exists with documentType: {} and documentNumber: {}. Error: {}",
                            documentType, documentNumber, e.getMessage());
                    return Mono.error(new UserAnyException("Error validando si el usuario existe"));
                })
                .hasElement();
    }

    @Override
    public Mono<Boolean> emailExists(String email) {
        return this.repository.findByEmail(email)
                .onErrorResume(e -> {
                    log.error("Error checking if email exists: {}. Error: {}", email, e.getMessage());
                    return Mono.error(new UserAnyException("Error validando si el correo existe"));
                })
                .hasElement();
    }

    @Override

    public Mono<Long> saveUser(User user) {
        return this.repository.save(toData(user))
                .map(UserEntity::getId)
                .onErrorResume(e -> {
                    log.error("Error saving user: {}. Error: {}", user, e.getMessage());
                    return Mono.error(new UserAnyException("Error guardando el usuario"));
                });
    }
}
