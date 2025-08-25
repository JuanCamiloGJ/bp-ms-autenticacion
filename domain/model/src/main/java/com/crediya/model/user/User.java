package com.crediya.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    private Long id;
    private String documentType;
    private String documentNumber;
    private String firstNames;
    private String lastNames;
    private String birthdate;
    private String email;
    private String phoneNumber;
    private String address;
    private Integer baseSalary;
}
