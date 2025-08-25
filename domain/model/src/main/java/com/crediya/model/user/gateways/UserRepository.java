package com.crediya.model.user.gateways;

import reactor.core.publisher.Mono;

import com.crediya.model.user.User;

public interface UserRepository {
    /**
     * Verifica si un usuario existe por su tipo y número de documento
     * @param documentType tipo de documento
     * @param documentNumber número de documento
     * @return true si el usuario existe, false en caso contrario
     */
    Mono<Boolean> userExists(String documentType, String documentNumber);
    /**
     * Verifica si el correo electrónico ya está registrado
     * @param email correo electrónico
     * @return true si el correo existe, false en caso contrario
     */
    Mono<Boolean> emailExists(String email);
    /**
     * Guarda un nuevo usuario en el sistema
     * @param user datos del usuario a guardar
     * @return el ID del usuario creado
     */
    Mono<Long> saveUser(User user);
}
