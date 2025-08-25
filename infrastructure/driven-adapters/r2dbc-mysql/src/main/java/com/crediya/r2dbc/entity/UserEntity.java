package com.crediya.r2dbc.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("users")
public class UserEntity {
    @Id
    private Long id;
    private String documentType;
    private String documentNumber;
    private String firstNames;
    private String lastNames;
    private LocalDate birthdate;
    private String email;
    private String phoneNumber;
    private String address;
    private Integer baseSalary;

}
