package com.patika.atmProject.app.customer.dto;

import com.patika.atmProject.app.general.enums.AccountStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerAccountDetailDto {

    private Long id;
    private Long customerTc;
    private String ibanNo;
    private BigDecimal amount;
    private AccountStatus accountStatus;
}
