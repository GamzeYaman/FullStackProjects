package com.patika.atmProject.app.customer.dto;

import com.patika.atmProject.app.customer.entity.CustomerDetail;
import com.patika.atmProject.app.general.enums.AccountStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerAccountDetailSaveDto {

    private Long customerTc;
    private String ibanNo;
    private BigDecimal amount;
    private AccountStatus accountStatus;
}
