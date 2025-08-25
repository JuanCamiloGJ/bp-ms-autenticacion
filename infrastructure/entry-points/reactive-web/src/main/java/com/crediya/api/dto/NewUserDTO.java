package com.crediya.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class NewUserDTO {
    private Long id;
    @NotBlank(message = "El tipo de documento es obligatorio")
    private String documentType;
    @NotBlank(message = "El número de documento es obligatorio")
    private String documentNumber;
    @NotBlank(message = "Los nombres son obligatorios")
    private String firstNames;
    @NotBlank(message = "Los apellidos son obligatorios")
    private String lastNames;
    @NotBlank(message = "La fecha de nacimiento es obligatoria")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha de nacimiento debe tener el formato YYYY-MM-DD")
    private String birthdate;
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico no es válido")
    private String email;
    @NotBlank(message = "El número de teléfono es obligatorio")
    private String phoneNumber;
    @NotBlank(message = "La dirección es obligatoria")
    private String address;
    @NotBlank(message = "Se requiere una contraseña")
    private String password;
    @NotNull(message = "El salario base es obligatorio")
    @Min(value = 0, message = "El salario base no puede ser negativo")
    @Max(value = 15000000, message = "El salario base no puede exceder 15.000.000")
    private Integer baseSalary;
}
