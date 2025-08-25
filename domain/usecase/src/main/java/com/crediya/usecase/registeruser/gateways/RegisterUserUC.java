package com.crediya.usecase.registeruser.gateways;

import reactor.core.publisher.Mono;

import com.crediya.model.user.User;

public interface RegisterUserUC {
    /**
     * Crea un nuevo usuario en el sistema
     * @param user datos del usuario a crear
     * @return el usuario creado
     */
    Mono<User> execute(User user);
}
