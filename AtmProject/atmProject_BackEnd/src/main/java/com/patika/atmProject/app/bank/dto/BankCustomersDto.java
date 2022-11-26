package com.patika.atmProject.app.bank.dto;

import com.patika.atmProject.app.general.enums.Banks;
import com.patika.atmProject.app.general.enums.Genders;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BankCustomersDto {

    private final String customerName;
    private final String customerSurname;
    private final Genders gender;
    private final String customerPhoneNumber;
    private final Banks bankName;

}
