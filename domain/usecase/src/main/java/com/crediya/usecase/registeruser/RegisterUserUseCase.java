package com.crediya.usecase.registeruser;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import com.crediya.model.user.User;
import com.crediya.model.user.exceptions.UserAnyException;
import com.crediya.model.user.gateways.UserRepository;
import com.crediya.usecase.registeruser.gateways.RegisterUserUC;

@RequiredArgsConstructor
public class RegisterUserUseCase implements RegisterUserUC {
    private final UserRepository userRepository;

    public Mono<User> execute(User user) {
        return Mono.zip(
                        userRepository.userExists(user.getDocumentType(), user.getDocumentNumber()),
                        userRepository.emailExists(user.getEmail())
                )
                .log()
                .flatMap(tuple -> {
                    boolean userExists = tuple.getT1();
                    boolean emailExists = tuple.getT2();
                    if (userExists) {
                        return Mono.error(new UserAnyException("El usuario ya esta registrado"));
                    }
                    if (emailExists) {
                        return Mono.error(new UserAnyException("El correo electrónico ya está registrado"));
                    }
                    return userRepository.saveUser(user)
                            .map(id -> {
                                user.setId(id);
                                return user;
                            });
                });

    }

}
