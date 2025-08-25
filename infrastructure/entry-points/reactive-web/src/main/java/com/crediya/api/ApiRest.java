package com.crediya.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crediya.api.dto.NewUserDTO;
import com.crediya.api.mapper.UserMapper;
import com.crediya.usecase.registeruser.gateways.RegisterUserUC;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class ApiRest {

    private final RegisterUserUC registerUserUC;
    private TransactionalOperator transactionalOperator;

    @PostMapping(path = "/usuarios")
    public Mono<ResponseEntity<NewUserDTO>> registerUser(@RequestBody @Valid NewUserDTO newUserDTO) {
        return registerUserUC.execute(UserMapper.INSTANCE.newUserDTOToUser(newUserDTO))
                .as(transactionalOperator::transactional)
                .doOnError(e -> log.error("Error registrando el usuario: {}", e.getMessage()))
                .map(createdUser -> ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.INSTANCE.userToNewUserDTO(createdUser)));
    }
}
