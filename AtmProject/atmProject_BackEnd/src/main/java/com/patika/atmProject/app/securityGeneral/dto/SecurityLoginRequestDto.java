package com.patika.atmProject.app.securityGeneral.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityLoginRequestDto {
    private Long customerTc;
    private String password;
}
