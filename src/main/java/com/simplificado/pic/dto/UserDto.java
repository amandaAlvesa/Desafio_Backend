package com.simplificado.pic.dto;

import java.math.BigDecimal;

import com.simplificado.pic.domain.UserType;

public record UserDto(String firstName, String lastName, String document, String email, String password, BigDecimal balance, UserType userType) {

}
