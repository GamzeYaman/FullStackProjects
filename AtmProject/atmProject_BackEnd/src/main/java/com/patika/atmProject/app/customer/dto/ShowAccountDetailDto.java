package com.patika.atmProject.app.customer.dto;

import com.patika.atmProject.app.general.enums.Banks;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class ShowAccountDetailDto {

    private final String customerName;
    private final String customerSurname;
    private final Banks bankName;
    private final String ibanNo;
    private final BigDecimal amount;
}
